package brandname.smpp_protocol.model.smpp_base_model;


import brandname.smpp_protocol.exceptions.InvalidPDUException;
import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;

public abstract class ByteData {

    private static double MAX_POSITIVE_NUMBER_A_SHORT_CAN_CONTAIN = Math.pow(2,16);
    private static double MAX_POSITIVE_NUMBER_A_BYTE_CAN_CONTAIN = Math.pow(2,8);

    public ByteData() {
    }

    public abstract void setData(ByteBuffer buffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException, InvalidPDUException;

    public abstract ByteBuffer getData() throws NotEnoughByteInByteBufferException;


    // encode là biến phần số dương của kiểu to hơn thành số signed cả âm cả dương (dùng bit dấu) của kiểu bé hơn.
    // decode là biến số signed số của kiểu bé hơn thành phần dương của kiểu to hơn.
    // ví dụ: số byte ở java là signed nhưng muốn biểu diễn số âm của byte thành dương
    // tức là vô hiệu hóa bit dấu => phải dùng kiểu to hơn là kiểu short để biểu diễn
    // khi đó phần âm của byte sẽ biểu diễn thành phần dương của short
    // điều này khả thi vì: số byte có 8 bit trong đó 1 bit dấu => biểu diễn đc 2^8 số , 2^7 dương và 2^7 âm
    // số short có 16 bit trong đó 1 bit dấu => biểu diễn đc 2^15 số, 2^15 > 2^7 + 2^7 => biểu diễn đc


    public short encodePositiveIntToSignedShort(int unsignedIntNumber) {
        // max_value cua short  = 2 ^(16-1) -1 ; 16-1 la tru di bit dau xac dinh +/-
        // -1 la tru di so 0.
        if (unsignedIntNumber <= Short.MAX_VALUE) {
            return (short) unsignedIntNumber;
        } else {
            // numberInPositive - MAX_POSITIVE_NUMBER_A_SHORT_CAN_CONTAIN
            // = numberInPositive - (Short.MAX - Short.Min)
            // = numberInPositive - Short.MAX + Short.Min
            // = (numberInPositive - Short.MAX) + Short.Min
            // = Số dư ra khi overflow  + Short.Min
            // = số bị overflow khi lơn hơn max.
            return (short) (unsignedIntNumber - MAX_POSITIVE_NUMBER_A_SHORT_CAN_CONTAIN);
        }
    }

    public int decodeSignedShortToPositiveInt(short signedShortNumber) {
        if(signedShortNumber >= 0) {
            return signedShortNumber;
        }else {
            return (int) ((double)signedShortNumber + MAX_POSITIVE_NUMBER_A_SHORT_CAN_CONTAIN);
        }
    }

    public byte encodePositiveShortToSignedByte(short positiveShort) {
        if(positiveShort <= Byte.MAX_VALUE) {
            return (byte) positiveShort;
        } else {
            return (byte) (positiveShort - MAX_POSITIVE_NUMBER_A_BYTE_CAN_CONTAIN);
        }
    }

    public short decodeSignedByteToPositiveShort(byte signedByteNumber) {
        if(signedByteNumber >= 0) {
             return signedByteNumber;
        }else {
            return (short) (signedByteNumber + MAX_POSITIVE_NUMBER_A_BYTE_CAN_CONTAIN);
        }
    }
}
