package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model;

import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Response;
import brandname.smpp_protocol.model.util.Constants;

public class BindReceiverResp extends BindResponse {




    public BindReceiverResp() {
        super(Constants.CommandId.BIND_RECEIVER_RESP);
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) {

    }
}
