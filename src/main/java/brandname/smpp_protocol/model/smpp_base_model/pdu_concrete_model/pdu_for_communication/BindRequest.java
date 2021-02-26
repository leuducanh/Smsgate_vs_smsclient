package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_communication;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Request;
import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.Address;
import brandname.smpp_protocol.model.util.FieldValidator;

import java.io.UnsupportedEncodingException;

import static brandname.smpp_protocol.model.util.Constants.DefaultPDUField.DEFAULT_EMPTY_FIELD;
import static brandname.smpp_protocol.model.util.Constants.SMPP_INTERFACE;

public abstract class BindRequest extends Request {

    private String systemId = DEFAULT_EMPTY_FIELD;
    private String password = DEFAULT_EMPTY_FIELD;
    private String systemType = DEFAULT_EMPTY_FIELD;
    private int interfaceVersion = SMPP_INTERFACE;
    private Address address = new Address();

    protected abstract boolean isReceiver();
    protected abstract boolean isTransmitter();

    public BindRequest(int commandId) {
        super(commandId);
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {
        systemId = byteBuffer.removeCString();
        FieldValidator.checkFieldStringLength("systemId", systemId, 0, 16);
        password = byteBuffer.removeCString();
        FieldValidator.checkFieldStringLength("password", password, 0, 9);
        systemType = byteBuffer.removeCString();
        FieldValidator.checkFieldStringLength("systemType", systemType, 0, 13);
        interfaceVersion = byteBuffer.removeInt();
        address.setData(byteBuffer);
    }

    public String getSystemId() {
        return systemId;
    }

    public String getPassword() {
        return password;
    }

    public String getSystemType() {
        return systemType;
    }

    public int getInterfaceVersion() {
        return interfaceVersion;
    }

    public Address getAddress() {
        return address;
    }
}
