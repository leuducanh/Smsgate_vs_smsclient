package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.receiver;

import brandname.smpp_protocol.model.smpp_base_model.Response;
import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.BindRequest;
import brandname.smpp_protocol.model.util.Constants;

public class BindReceiver extends BindRequest {

    public BindReceiver() {
        super(Constants.CommandId.BIND_RECEIVER);
    }

    @Override
    protected Response createResponse() {
        return new BindReceiverResp();
    }

    @Override
    protected boolean isReceiver() {
        return true;
    }

    @Override
    protected boolean isTransmitter() {
        return false;
    }
}
