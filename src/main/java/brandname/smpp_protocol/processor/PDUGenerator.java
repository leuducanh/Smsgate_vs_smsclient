package brandname.smpp_protocol.processor;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.PDU;
import brandname.smpp_protocol.model.smpp_base_model.PDUHeader;
import brandname.smpp_protocol.model.smpp_base_model.TLV;
import brandname.smpp_protocol.model.util.Constants;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PDUGenerator {

    private static LinkedList<PDU> pduTemplates = new LinkedList<>();
    private static LinkedList<TLV> defaultTLVs;
    private static Map<Integer, Class> commandIdToPDUType = new ConcurrentHashMap<>();

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
            throw new PDUHeaderIncomplete();
        }

        int commandId = pduHeader.getCommandId();
        PDU pduCloneFromTemplate = findPDUTemplateAndClone(commandId);

        ByteBuffer pduByteBufferBlock =  byteBuffer.removeBytes(pduHeader.getCommandLength());
        if (pduCloneFromTemplate != null) {
            try {
                pduCloneFromTemplate.setData(pduByteBufferBlock);
            } catch (InvalidPDUException e) {
                // không thể bị nếu bị thì bỏ qua do đã cắt đúng số lượng cần trong command length
            }
        } else {
            throw new UnknownCommandIdException(commandId);
        }

        return pduCloneFromTemplate;
    }

    private static PDU findPDUTemplateAndClone(int commandId) {

        Class pduType = commandIdToPDUType.get(commandId);
        try {
            return (PDU)(pduType.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
