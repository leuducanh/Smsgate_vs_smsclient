package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.transmitter;

import brandname.smpp_protocol.model.smpp_base_model.Response;
import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.BindRequest;

import static brandname.smpp_protocol.model.util.Constants.CommandId.BIND_TRANSMITTER;


public class BindTransmitterRequest extends BindRequest {

    public BindTransmitterRequest(int commandId) {
        super(BIND_TRANSMITTER);
    }

    @Override
    protected Response createResponse() {
        return new BindTransmitterResponse();
    }

    @Override
    protected boolean isReceiver() {
        return false;
    }

    @Override
    protected boolean isTransmitter() {
        return false;
    }
}
