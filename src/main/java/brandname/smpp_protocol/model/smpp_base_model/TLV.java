package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;

public abstract class TLV extends ByteData{

    private short tag = 0;

    public TLV(short tag) {
        super();
        this.tag = tag;
    }

    @Override
    public void setData(ByteBuffer byteBuffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException {
        tag = byteBuffer.removeShort();
        int length = byteBuffer.removeShort();
        setDataValuePart(byteBuffer.removeBytes(length));
    }

    @Override
    public ByteBuffer getData() {

        ByteBuffer byteBuffer = new ByteBuffer();
        byteBuffer.appendShort(tag);
        byteBuffer.appendShort(encodeUnsignedInt(getDataPartLength()));
        byteBuffer.appendBytesDirectlyToBuffer();
        return null;
    }

    public abstract void setDataValuePart(ByteBuffer byteBuffer);
    public abstract ByteBuffer getDataPart();
    public abstract int getDataPartLength();

}
