package brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.ValueNotSetException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.ByteData;
import brandname.smpp_protocol.model.smpp_base_model.TLV;
import brandname.smpp_protocol.model.util.Constants;

import static brandname.smpp_protocol.model.ByteBuffer.SZ_SHORT;

public class TLVShort extends TLV {

    private Short value;

    public TLVShort(short tag) {
        super(tag);
    }

    @Override
    public void setValueFromThisByteBuffer(ByteBuffer valueWrapInByteBuffer) throws NotEnoughByteInByteBufferException {
        this.value = valueWrapInByteBuffer.removeShort();
    }

    @Override
    public ByteBuffer getValueWrapInByteBuffer() throws ValueNotSetException {
        ByteBuffer byteBuffer = new ByteBuffer();
        byteBuffer.appendShort(value);
        return byteBuffer;
    }

    @Override
    public int getValueLength() {
        return SZ_SHORT;
    }
}
