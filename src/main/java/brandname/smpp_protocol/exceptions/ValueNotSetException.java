package brandname.smpp_protocol.exceptions;

import brandname.smpp_protocol.model.smpp_base_model.PDU;

public class ValueNotSetException extends PDUException {
    public ValueNotSetException(PDU pdu) {
        super(pdu);
    }

    public ValueNotSetException() {
        super();
    }
}
