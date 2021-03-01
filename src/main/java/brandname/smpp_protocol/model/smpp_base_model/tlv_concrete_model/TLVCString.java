package brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.exceptions.ValueNotSetException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.TLV;

public class TLVCString extends TLV {

    private String stringValue;

    public TLVCString(short tag) {
        super(tag);
    }

    @Override
    public void setValueFromThisByteBuffer(ByteBuffer valueWrapInByteBuffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException {
        this.stringValue = valueWrapInByteBuffer.removeCString();
    }

    @Override
    public ByteBuffer getValueWrapInByteBuffer() throws ValueNotSetException {
        ByteBuffer byteBuffer = new ByteBuffer();
        byteBuffer.appendCString(stringValue);
        return byteBuffer;
    }

    @Override
    public int getValueLength() {
        return stringValue.length();
    }
}
