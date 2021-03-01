package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.exceptions.ValueNotSetException;
import brandname.smpp_protocol.model.ByteBuffer;

public abstract class TLV extends ByteData{

    private short tag = 0;

    // vi gia tri value la primative nen se ko biet no duoc set chua neu chi xem xet gia tri
    // do khong co gia tri null.
    public boolean hasValue;

    public TLV(short tag) {
        super();
        this.tag = tag;
    }

    @Override
    public void setData(ByteBuffer byteBuffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException {
        tag = byteBuffer.removeShort();
        int length = byteBuffer.removeShort();
        ByteBuffer valueWrapInByteBuffer = byteBuffer.removeBytes(length);
        setValueFromThisByteBuffer(valueWrapInByteBuffer);
    }

    @Override
    public ByteBuffer getData() throws NotEnoughByteInByteBufferException {
        ByteBuffer byteBuffer = new ByteBuffer();
        byteBuffer.appendShort(tag);
        byteBuffer.appendShort(encodePositiveIntToSignedShort(getValueLength()));
        try {
            byteBuffer.appendOtherByteBuffer(getValueWrapInByteBuffer());
        } catch (ValueNotSetException e) {
            e.printStackTrace();
        }
        return byteBuffer;
    }

    public abstract void setValueFromThisByteBuffer(ByteBuffer valueWrapInByteBuffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException;
    public abstract ByteBuffer getValueWrapInByteBuffer() throws ValueNotSetException;
    public abstract int getValueLength();

    public short getTag() {
        return tag;
    }
}
