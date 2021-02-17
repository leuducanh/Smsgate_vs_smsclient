package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model;

import brandname.smpp_protocol.exceptions.InvalidPDUException;
import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.ByteData;
import brandname.smpp_protocol.model.util.Constants;
import jdk.vm.ci.meta.Constant;

import java.io.UnsupportedEncodingException;

public class Address extends ByteData {

    private byte ton;
    private byte npi;
    private String addressRange;


    public Address() {
        ton = Constants.TonData.Unknown;
        npi = Constants.TonData.Unknown;
        addressRange = Constants.DefaultPDUField.DEFAULT_EMPTY_FIELD;
    }

    @Override
    public void setData(ByteBuffer buffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException, InvalidPDUException, UnsupportedEncodingException {
        ton = buffer.removeByte();
        npi = buffer.removeByte();
        addressRange = buffer.removeCString();
    }

    @Override
    public ByteBuffer getData() throws NotEnoughByteInByteBufferException {
        ByteBuffer byteBuffer = new ByteBuffer();
        byteBuffer.appendByte(ton);
        byteBuffer.appendByte(npi);
        byteBuffer.appendCString(addressRange);
        return byteBuffer;
    }

    public int getTon() {
        return ton;
    }


    public int getNpi() {
        return npi;
    }


    public String getAddressRange() {
        return addressRange;
    }

    public void setAddressRange(String addressRange) {
        this.addressRange = addressRange;
    }
}
