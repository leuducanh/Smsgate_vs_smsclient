package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.exceptions.ValidateException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Response;
import brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model.TLVByte;
import brandname.smpp_protocol.model.util.FieldValidator;

import static brandname.smpp_protocol.model.util.Constants.DefaultPDUField.DEFAULT_EMPTY_FIELD;
import static brandname.smpp_protocol.model.util.Constants.DefaultTVLField.sc_interface_version;

public class BindResponse extends Response {

    private String systemId = DEFAULT_EMPTY_FIELD;

    private TLVByte scInterfaceVersion = new TLVByte(sc_interface_version);

    public BindResponse(int commandId) {
        super(commandId);
        defaultTlvOfThisPdu.add(scInterfaceVersion);
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException {
        systemId = byteBuffer.removeCString();
        FieldValidator.checkFieldStringLength("systemId", systemId, 0, 16);
    }
}
