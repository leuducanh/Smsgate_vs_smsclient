package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication;

import brandname.smpp_protocol.model.smpp_base_model.Response;

import static brandname.smpp_protocol.model.util.Constants.CommandId.BIND_TRANSCEIVER;

public class BindTransceiverRequest extends BindRequest{
    public BindTransceiverRequest() {
        super(BIND_TRANSCEIVER);
    }

    @Override
    protected Response createResponse() {
        return new BindTransceiverResponse();
    }

    @Override
    protected boolean isReceiver() {
        return true;
    }

    @Override
    protected boolean isTransmitter() {
        return true;
    }
}
