package brandname.smpp_protocol.exceptions;

public class NotEnoughByteInByteBufferException extends SmppException{

    private int remainNumberOfBytes;
    private int expectedNumberOfBytes;

    public NotEnoughByteInByteBufferException(int remainNumberOfBytes, int expectedNumberOfBytes) {
        super("number remain" + remainNumberOfBytes + " expect " + expectedNumberOfBytes);
        this.remainNumberOfBytes = remainNumberOfBytes;
        this.expectedNumberOfBytes = expectedNumberOfBytes;
    }
}
