package brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.ValueNotSetException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.TLV;

// unsigned byte -> đây là số dương  -> chứa vào short thì mới xử lí như số dương được
// vì java thì byte là signed byte.
public class TLVUByte extends TLV {

    private short unsignedByteValue;

    public TLVUByte(short tag) {
        super(tag);
    }

    @Override
    public void setValueFromThisByteBuffer(ByteBuffer valueWrapInByteBuffer) throws NotEnoughByteInByteBufferException {
        byte value = valueWrapInByteBuffer.removeByte();
        short valueConvertToShort = decodeSignedByteToPositiveShort(value);
        setValue(valueConvertToShort);
    }

    @Override
    public ByteBuffer getValueWrapInByteBuffer() throws ValueNotSetException {
        ByteBuffer wrapperByteBuffer = new ByteBuffer();
        wrapperByteBuffer.appendByte(encodePositiveShortToSignedByte(getValue()));
        return wrapperByteBuffer;
    }

    @Override
    public int getValueLength() {
        return ByteBuffer.SZ_INT;
    }

    private void setValue(short value){
        hasValue = true;
        this.unsignedByteValue = value;
    }

    private short getValue() throws ValueNotSetException {
        if(hasValue) {
            return unsignedByteValue;
        }else{
            throw new ValueNotSetException();
        }
    }
}
