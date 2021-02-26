package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.receiver;

import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication.BindResponse;
import brandname.smpp_protocol.model.util.Constants;

public class BindReceiverResp extends BindResponse {

    public BindReceiverResp() {
        super(Constants.CommandId.BIND_RECEIVER_RESP);
    }

}
