package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.transceiver;

import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.BindResponse;

import static brandname.smpp_protocol.model.util.Constants.CommandId.BIND_TRANSCEIVER_RESP;

public class BindTransceiverResponse extends BindResponse {
    public BindTransceiverResponse() {
        super(BIND_TRANSCEIVER_RESP);
    }
}
