package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.enquire_link;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Request;
import brandname.smpp_protocol.model.smpp_base_model.Response;

import java.io.UnsupportedEncodingException;

import static brandname.smpp_protocol.model.util.Constants.CommandId.ENQUIRE_LINK;

public class EnquireLinkRequest extends Request {

    public EnquireLinkRequest() {

        super(ENQUIRE_LINK);
    }

    @Override
    protected Response createResponse() {
        return null;
    }


    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {

    }
}
