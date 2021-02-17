package brandname.smpp_protocol.exceptions;

import brandname.smpp_protocol.model.smpp_base_model.PDU;

public class PDUException  extends SmppException{
    private transient PDU pdu;


    public PDUException() {
        super();
    }

    public PDUException(PDU pdu) {
        super();
        this.pdu = pdu;
    }

    @Override
    public String toString() {
        return "PDUException{" +
                "pdu=" + pdu +
                '}';
    }

    public PDU getPdu() {
        return pdu;
    }

    public void setPdu(PDU pdu) {
        this.pdu = pdu;
    }
}
