package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model.TLVOctets;
import brandname.smpp_protocol.model.util.Constants;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PDU extends ByteData {

    private static AtomicInteger sequenceNumberGlobal = new AtomicInteger(0);

    public static final byte VALID_NONE = 0;
    public static final byte VALID_HEADER = 1;
    public static final byte VALID_HEADER_AND_BODY = 2;
    public static final byte VALID_HEADER_AND_BODY_AND_OPTIONAL_PARAM = 3;

    public byte state = -1;

    private LinkedList<TLV> defaultTlvOfThisPdu = new LinkedList<>();

    private LinkedList<TLV> tLVListForUnknownTag = new LinkedList<>();

    private PDUHeader pduHeader;

    public PDU() {
        super();
    }

    public PDU(int commandId) {
        checkHeader();
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
    }

    protected abstract void setBody(ByteBuffer byteBuffer);

    private void checkHeader() {
        if (pduHeader == null) {
            pduHeader = new PDUHeader();
        }
    }

    private void setState(byte state) {
        this.state = state;
    }

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

            if (pduHeader.getCommandLength() - Constants.PDU_HEADER_SIZE < byteBuffer.length()) {
                // not enough data
            }

            setBody(byteBuffer);

            if (totalByteLength - byteBuffer.length() < pduHeader.getCommandLength()) {
                int extraTLVLength = pduHeader.getCommandLength()
                        - (totalByteLength - byteBuffer.length());
                setOptionalTLV(byteBuffer.removeBytes(extraTLVLength));
            }
        } catch (NotEnoughByteInByteBufferException | TerminatingZeroNotFoundException e) {
            throw new InvalidPDUException(this, e);
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
