package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.enquire_link;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Response;

import java.io.UnsupportedEncodingException;

public class EnquireLinkResponse extends Response {


    public EnquireLinkResponse(int commandId) {
        super(commandId);
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {

    }
}
