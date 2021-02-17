package brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.ValueNotSetException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.TLV;

public class TLVInt extends TLV {

    private int intValue;

    public TLVInt(short tag) {
        super(tag);
    }

    @Override
    public void setValueFromThisByteBuffer(ByteBuffer valueWrapInByteBuffer) throws NotEnoughByteInByteBufferException {
        int value = valueWrapInByteBuffer.removeInt();
        setValue(value);
    }

    @Override
    public ByteBuffer getValueWrapInByteBuffer() throws ValueNotSetException {
        ByteBuffer wrapperByteBuffer = new ByteBuffer();
        wrapperByteBuffer.appendInt(getValue());
        return wrapperByteBuffer;
    }

    @Override
    public int getValueLength() {
        return ByteBuffer.SZ_INT;
    }

    private void setValue(int value){
        hasValue = true;
        this.intValue = value;
    }

    private int getValue() throws ValueNotSetException {
        if(hasValue) {
            return intValue;
        }else{
            throw new ValueNotSetException();
        }
    }
}
