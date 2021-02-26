package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_message_tranferring;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.ByteData;
import brandname.smpp_protocol.model.util.FieldValidator;

import java.io.UnsupportedEncodingException;

import static brandname.smpp_protocol.model.util.Constants.DefaultPDUField.DEFAULT_NULL_FIELD;

public class ShortMessage extends ByteData {

    private Integer sm_length;
    // nếu lớn hơn 254 kí tự thì length set = 0 và dùng message payload thay thế
    private byte[] short_message;

    public ShortMessage() {
        sm_length = (Integer) DEFAULT_NULL_FIELD;
        short_message = null;
    }

    @Override
    public void setData(ByteBuffer buffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException, InvalidPDUException, UnsupportedEncodingException, ValidateException {

        sm_length = buffer.removeInt();
        short_message  = buffer.removeBytes(sm_length).getBuffer();
        FieldValidator.checkFieldBytesLength("short_message", short_message, 0,254);
    }

    @Override
    public ByteBuffer getData() throws NotEnoughByteInByteBufferException {
        return null;
    }
}
