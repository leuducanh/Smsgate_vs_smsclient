package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.enquire_link;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Request;
import brandname.smpp_protocol.model.smpp_base_model.Response;

import java.io.UnsupportedEncodingException;

public class EnquireLinkRequest extends Request {

    public EnquireLinkRequest() {
        super();
    }

    @Override
    protected Response createResponse() {
        return null;
    }


    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {

    }
}
