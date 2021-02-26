package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.transmitter;

import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.BindResponse;

import static brandname.smpp_protocol.model.util.Constants.CommandId.BIND_TRANSMITTER_RESP;

public class BindTransmitterResponse extends BindResponse {
    public BindTransmitterResponse() {
        super(BIND_TRANSMITTER_RESP);
    }
}
