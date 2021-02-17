package brandname.smpp_protocol.exceptions;

public class UnknownCommandIdException extends Throwable {

    private int commandId;

    public UnknownCommandIdException() {
    }

    public UnknownCommandIdException(int commandId) {
        super("Unknown command id = " + commandId);
        this.commandId = commandId;
    }
}
