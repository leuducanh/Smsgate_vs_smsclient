package brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.ValueNotSetException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.TLV;

public class TLVByte extends TLV {
    private byte byteValue;

    public TLVByte(short tag) {
        super(tag);
    }

    @Override
    public void setValueFromThisByteBuffer(ByteBuffer valueWrapInByteBuffer) throws NotEnoughByteInByteBufferException {
        byte value = valueWrapInByteBuffer.removeByte();
        setValue(value);
    }

    @Override
    public ByteBuffer getValueWrapInByteBuffer() throws ValueNotSetException {
        ByteBuffer wrapperByteBuffer = new ByteBuffer();
        wrapperByteBuffer.appendByte(getValue());
        return wrapperByteBuffer;
    }

    @Override
    public int getValueLength() {
        return 1;
    }

    private void setValue(byte value){
        hasValue = true;
        this.byteValue = value;
    }

    private byte getValue() throws ValueNotSetException {
        if(hasValue) {
            return byteValue;
        }else{
            throw new ValueNotSetException();
        }
    }


}
