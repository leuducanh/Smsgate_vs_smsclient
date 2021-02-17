package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model;

import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Request;
import brandname.smpp_protocol.model.smpp_base_model.Response;
import brandname.smpp_protocol.model.util.Constants;

public class BindReceiver extends Request {

    public BindReceiver() {
        super(Constants.CommandId.BIND_RECEIVER);
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) {

    }

    @Override
    protected Response createResponse() {
        return new BindReceiverResp();
    }
}
