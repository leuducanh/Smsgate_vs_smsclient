package brandname.smpp_protocol.exceptions;

public class TerminatingZeroNotFoundException extends SmppException{

    public TerminatingZeroNotFoundException() {
        super("Terminating zero not found");
    }

    public TerminatingZeroNotFoundException(String message) {
        super(message);
    }
}
