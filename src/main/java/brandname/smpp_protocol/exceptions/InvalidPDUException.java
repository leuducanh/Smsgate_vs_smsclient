package brandname.smpp_protocol.exceptions;

import brandname.smpp_protocol.model.smpp_base_model.PDU;

public class InvalidPDUException extends SmppException{

    private PDU pdu;
    private SmppException exception;

    public InvalidPDUException(PDU pdu, SmppException exception) {
        this.pdu = pdu;
        this.exception = exception;
    }

    public InvalidPDUException(PDU pdu, String message) {
        super(message);
        this.pdu = pdu;
    }
}
