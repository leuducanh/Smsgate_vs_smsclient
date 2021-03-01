package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_message_tranferring.submit_sm;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Response;

import java.io.UnsupportedEncodingException;

import static brandname.smpp_protocol.model.util.Constants.DefaultPDUField.DEFAULT_EMPTY_FIELD;
import static org.smpp.Data.SUBMIT_SM_RESP;

public class SubmitSMResp extends Response {

    private String message_id = DEFAULT_EMPTY_FIELD;

    public SubmitSMResp() {
        super(SUBMIT_SM_RESP);
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {
        if(pduHeader.getCommandStatus() == 0) {
            message_id = byteBuffer.removeCString();
            return;
        }

        if(byteBuffer.length() > 0) {
            byteBuffer.removeBytes(byteBuffer.length());
        }
    }
}
