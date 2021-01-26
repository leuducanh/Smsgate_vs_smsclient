package brandname.smpp_protocol.model.smpp_base_model;


import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;

public abstract class ByteData {

    private static double MAX_POSITIVE_NUMBER_A_SHORT_CAN_CONTAIN = Math.pow(2,16);

    public ByteData() {
    }

    public abstract void setData(ByteBuffer buffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException;

    public abstract ByteBuffer getData();



    public short encodeUnsignedInt(int numberInPositive) {
        // max_value cua short  = 2 ^(16-1) -1 ; 16-1 la tru di bit dau xac dinh +/-
        // -1 la tru di so 0.
        if (numberInPositive < Short.MAX_VALUE) {
            return (short) numberInPositive;
        } else {
            return (short) (-(MAX_POSITIVE_NUMBER_A_SHORT_CAN_CONTAIN - numberInPositive));
        }
    }

    public int decodeUnsignedInt(short number) {
        if(number >= 0) {
            return number;
        }else {

            return 1;
        }
    }
}
