package brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.ValueNotSetException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.TLV;

public class TLVOctets extends TLV {

    private ByteBuffer byteBufferValue;

    public TLVOctets(short tag) {
        super(tag);
    }

    @Override
    public void setValueFromThisByteBuffer(ByteBuffer valueWrapInByteBuffer)  {
        ByteBuffer byteBuffer = null;
        try {
            byteBuffer = valueWrapInByteBuffer.removeBytes(valueWrapInByteBuffer.length());
        } catch (NotEnoughByteInByteBufferException e) {
            e.printStackTrace();
        }
        setValue(byteBuffer);
    }

    @Override
    public ByteBuffer getValueWrapInByteBuffer() throws ValueNotSetException {
        ByteBuffer wrapperByteBuffer = new ByteBuffer();
        try {
            wrapperByteBuffer.appendOtherByteBuffer(getValue());
        } catch (NotEnoughByteInByteBufferException e) {
            e.printStackTrace();
        }
        return wrapperByteBuffer;
    }

    @Override
    public int getValueLength() {
        return byteBufferValue.length();
    }

    private void setValue(ByteBuffer byteBuffer){
        hasValue = true;
        this.byteBufferValue = byteBuffer;
    }

    private ByteBuffer getValue() throws ValueNotSetException {
        if(hasValue) {
            return byteBufferValue;
        }else{
            throw new ValueNotSetException();
        }
    }
}
