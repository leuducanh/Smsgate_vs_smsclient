package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model.TLVOctets;
import brandname.smpp_protocol.model.util.Constants;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PDU extends ByteData {

    private static int sequenceNumberGlobal = 0;
    private int sequence = -1;

    public static final byte VALID_NONE = 0;
    public static final byte VALID_HEADER = 1;
    public static final byte VALID_HEADER_AND_BODY = 2;
    public static final byte VALID_HEADER_AND_BODY_AND_OPTIONAL_PARAM = 3;

    public byte state = -1;

    protected LinkedList<TLV> defaultTlvOfThisPdu = new LinkedList<>();

    private LinkedList<TLV> tLVListForUnknownTag = new LinkedList<>();

    protected PDUHeader pduHeader;

    private boolean sequenceBeAssigned = false;

    public PDU() {
        super();
    }

    public PDU(int commandId) {
        checkHeader();
        setCommandId(commandId);
    }

    public void setCommandId(int commandId) {
        checkHeader();
        pduHeader.setCommandId(commandId);
    }

    private void setHeader(ByteBuffer headerByteBuffer) throws NotEnoughByteInByteBufferException {
        if (this.pduHeader == null) {
            pduHeader = new PDUHeader();
        }
        this.pduHeader.setData(headerByteBuffer);
        sequenceBeAssigned = true;
    }

    protected abstract void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException;

    private void checkHeader() {
        if (pduHeader == null) {
            pduHeader = new PDUHeader();
        }
    }

    private void assignSequence(int newSequence) {
        this.sequence = newSequence;
    }

    protected void assignSequenceWithNextSequence() {
        if (!sequenceBeAssigned) {
            assignSequence(generateSequence());
            sequenceBeAssigned = true;
        }
    }

    private void setState(byte state) {
        this.state = state;
    }

    private Integer generateSequence() {
        return ++sequenceNumberGlobal | 0xFFFFFFFF;
    }

//    public static void main(String[] args) {
//        int a = Integer.MAX_VALUE;
//        System.out.println(Integer.toBinaryString(a+1));
//        System.out.println((a+1) & 0x7FFFFFFF);
//    }

    @Override
    public void setData(ByteBuffer byteBuffer) throws PDUException, InvalidPDUException {
        setState(VALID_NONE);

        int totalByteLength = byteBuffer.length();
        if (Constants.PDU_HEADER_SIZE < byteBuffer.length()) {
//            throw new NotEnoughByteInByteBufferException(byteBuffer.length()
//                    , Constants.PDU_HEADER_SIZE);
            // not enough data
        }
        try {
            ByteBuffer headerByteBuffer = byteBuffer.removeBytes(Constants.PDU_HEADER_SIZE);

            setHeader(headerByteBuffer);

            setState(VALID_HEADER);

            setBody(byteBuffer);

            setState(VALID_HEADER_AND_BODY);

            if (totalByteLength - byteBuffer.length() < pduHeader.getCommandLength()) {
                int extraTLVLength = pduHeader.getCommandLength()
                        - (totalByteLength - byteBuffer.length());
                setOptionalTLV(byteBuffer.removeBytes(extraTLVLength));
            }

            setState(VALID_HEADER_AND_BODY_AND_OPTIONAL_PARAM);
        } catch (TerminatingZeroNotFoundException | NotEnoughByteInByteBufferException e) {
            throw new InvalidPDUException(this, e);
        } catch (ValidateException e) {
            throw new InvalidPDUException(this, e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (totalByteLength - byteBuffer.length() != pduHeader.getCommandLength()) {
            throw new InvalidPDUException(this, "Difference size parsed");
        }
    }

    private void setOptionalTLV(ByteBuffer byteBuffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException {
        short tag;
        short valueLength;

        while (byteBuffer.length() > 0) {
            ByteBuffer tlvHeader = byteBuffer.readByteBuffer(Constants.TLV_LENGTH_FIELD_SIZE + Constants.TLV_TAG_FIELD_SIZE);
            tag = tlvHeader.removeShort();
            TLV tlv = findInOptionalTLVList(tag);

            if (tlv == null) {
                TLVOctets tlvOctets = new TLVOctets(tag);
                tLVListForUnknownTag.add(tlvOctets);
            }
            valueLength = tlvHeader.removeShort();
            ByteBuffer tlvByteBuffer = byteBuffer.removeBytes(Constants.TLV_LENGTH_FIELD_SIZE
                    + Constants.TLV_TAG_FIELD_SIZE
                    + valueLength);

            tlv.setData(tlvByteBuffer);
        }
    }

    private TLV findInOptionalTLVList(short tag) {
        for (TLV tlv : defaultTlvOfThisPdu) {
            if (tlv.getTag() == tag) {
                return tlv;
            }
        }

        return null;
    }

    @Override
    public ByteBuffer getData() {

        return null;
    }
}
