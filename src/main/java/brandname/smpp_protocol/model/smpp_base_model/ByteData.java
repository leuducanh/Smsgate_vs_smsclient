package brandname.smpp_protocol.model.smpp_base_model;


import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;

public abstract class ByteData {


    public ByteData() {
    }

    public abstract void setData(ByteBuffer buffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException;

    public abstract ByteBuffer getData();

    public short encodeUnsignedInt(int numberInPositive) {

        return 1;
    }
}
