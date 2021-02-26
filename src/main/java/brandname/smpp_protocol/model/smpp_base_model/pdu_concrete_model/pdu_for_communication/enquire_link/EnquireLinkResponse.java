package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.enquire_link;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Response;

import java.io.UnsupportedEncodingException;

import static brandname.smpp_protocol.model.util.Constants.CommandId.ENQUIRE_LINK_RESP;

public class EnquireLinkResponse extends Response {


    public EnquireLinkResponse() {
        super(ENQUIRE_LINK_RESP);
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {

    }
}
