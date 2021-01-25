package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;

public class TLV extends ByteData{
    @Override
    public void setData() throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException {

    }

    @Override
    public ByteBuffer getData() {
        return null;
    }
}
