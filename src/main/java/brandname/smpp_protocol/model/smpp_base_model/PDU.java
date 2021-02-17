package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.util.Constants;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class PDU extends ByteData {


    public static final byte VALID_NONE = 0;
    public static final byte VALID_HEADER = 1;
    public static final byte VALID_HEADER_AND_BODY = 2;
    public static final byte VALID_HEADER_AND_BODY_AND_OPTIONAL_PARAM = 3;

    public byte state = -1;


    private static AtomicInteger sequenceNumberGlobal = new AtomicInteger(0);
    private static LinkedList<PDU> pduTemplates = new LinkedList<>();
    private LinkedList<TLV> defaultTlvOfThisPdu = new LinkedList<>();

    private static LinkedList<TLV> defaultTLVs;

    private LinkedList<TLV> extraTLVs;

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




    public static PDU createPDU(ByteBuffer byteBuffer) throws PDUHeaderIncomplete, NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException, UnknownCommandIdException {


        ByteBuffer headerByteBuffer = null;
        PDUHeader pduHeader;
        try {
            headerByteBuffer = byteBuffer.readByteBuffer(Constants.PDU_HEADER_SIZE);
            pduHeader = new PDUHeader();
            pduHeader.setData(headerByteBuffer);
        } catch (NotEnoughByteInByteBufferException e) {
            throw new PDUHeaderIncomplete();
        }

        if (byteBuffer.length() < pduHeader.getCommandLength()) {
            throw new NotEnoughByteInByteBufferException(byteBuffer.length(), pduHeader.getCommandLength());
        }

        int commandId = pduHeader.getCommandId();
        PDU pdu = selectPDUTemplate(commandId);

        if (pdu != null) {
            pdu.setData(byteBuffer);
        } else {
            throw new UnknownCommandIdException(commandId);
        }
    }

    private void setHeader(ByteBuffer headerByteBuffer) throws NotEnoughByteInByteBufferException {
        if (this.pduHeader == null) {
            pduHeader = new PDUHeader();
        }
        this.pduHeader.setData(headerByteBuffer);
    }

    protected abstract void setBody(ByteBuffer byteBuffer);

    static {


    }

    public static PDU selectPDUTemplate(int commandId) {
        for (PDU pduTemplate : pduTemplates) {
            if (pduTemplate.pduHeader.getCommandId() == commandId) {
                try {
                    return (PDU) (pduTemplate.getClass().newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    private void checkHeader() {
        if (pduHeader == null) {
            pduHeader = new PDUHeader();
        }
    }

    private void setState(byte state) {
        this.state = state;
    }

    @Override
    public void setData(ByteBuffer byteBuffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException {
        setState(VALID_NONE);

        int totalByteLength = byteBuffer.length();
        if (Constants.PDU_HEADER_SIZE < byteBuffer.length()) {
//            throw new NotEnoughByteInByteBufferException(byteBuffer.length()
//                    , Constants.PDU_HEADER_SIZE);
            // not enough data
        }
        ByteBuffer headerByteBuffer = byteBuffer.removeBytes(Constants.PDU_HEADER_SIZE);

        setHeader(headerByteBuffer);

        setState(VALID_HEADER);

        if (pduHeader.getCommandLength() - Constants.PDU_HEADER_SIZE < byteBuffer.length()) {
            // not enough data
        }

        setBody(byteBuffer);

        if(totalByteLength - byteBuffer.length() < pduHeader.getCommandLength()) {
            int extraTLVLength = pduHeader.getCommandLength()
                    - (totalByteLength - byteBuffer.length());
            setExtraTLV(byteBuffer.removeFromByteBuffer(extraTLVLength));
        }

    }

    private void setExtraTLV(ByteBuffer byteBuffer) {
        short tag;
        short length;

        while(byteBuffer.length() > 0) {

        }
    }

    @Override
    public ByteBuffer getData() {

        return null;
    }
}
