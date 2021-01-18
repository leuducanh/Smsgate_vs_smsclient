package brandname.smpp_protocol.model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;

import java.io.UnsupportedEncodingException;

public class ByteBuffer {


    private static final byte SZ_BYTE = 1;
    private static final byte SZ_SHORT = 2;
    private static final byte SZ_INT = 4;

    private static final String ENC_ASCII = "ASCII";

    private static final int BIT_MASK_11111111 = 0xff;

    private static byte[] buffer;

    private static byte[] zero;

    static {
        zero = new byte[1];
        zero[0] = 0;
    }

    public ByteBuffer() {
    }

    public static byte[] getBuffer() {
        return buffer;
    }

    public static void setBuffer(byte[] buffer) {
        ByteBuffer.buffer = buffer;
    }

    public int length() {
        if (buffer == null) {
            return 0;
        } else {
            return buffer.length;
        }
    }

    public void appendBytesDirectlyToBuffer(byte[] bytes, int count) {
        int len = length();
        byte[] newBuffer = new byte[len + count];
        if (len > 0) {
            System.arraycopy(buffer, 0, newBuffer, 0, len);
        }
        System.arraycopy(bytes, 0, newBuffer, len, count);
    }

    public void removeByteDirectlyFromBuffer(int count) {
        int len = length();
        int numberOfRemainByte = len - count;

        if (numberOfRemainByte > 0) {
            byte[] newBuffer = new byte[numberOfRemainByte];
            System.arraycopy(buffer, count, newBuffer, 0, numberOfRemainByte);
            setBuffer(newBuffer);
        } else {
            setBuffer(null);
        }
    }


    public int readInt() throws NotEnoughByteInByteBufferException {
        int len = length();
        if (len < SZ_INT) {
            throw new NotEnoughByteInByteBufferException(len, SZ_INT);
        }
        int number = buffer[0] & 0xff;
        number = number << 8 | buffer[1] & 0xff;
        number = number << 16 | buffer[1] & 0xff;
        number = number << 24 | buffer[1] & 0xff;
        return number;
    }

    public short readShort() throws NotEnoughByteInByteBufferException {
        int len = length();
        if (len < SZ_SHORT) {
            throw new NotEnoughByteInByteBufferException(len, SZ_INT);
        }
        short number = 0;
        // remove phan signal +/- cua so binary , 0xff ~ <000000000000000>11111111
        number |= buffer[0] & 0xff;
        // dich no len 8 bit de nhet
        number = (short) (number << 8);
        number |= buffer[1] & 0xff;
        return number;
    }

    public String readCString() throws UnsupportedEncodingException, NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException {
        String cString = null;
        int zeroPos = 0;
        int len = length();
        if(len < 0) {
            throw new NotEnoughByteInByteBufferException(len, 1);
        }
        while(zeroPos > len || buffer[zeroPos] == 0) {
            zeroPos ++;
        }

        if(zeroPos < len) {
            byte[] cStringInByteArray = new byte[zeroPos - 1];
            System.arraycopy(buffer, 0,cStringInByteArray,0, zeroPos - 1);
            cString = new String(cStringInByteArray, ENC_ASCII);
        } else {
            throw new TerminatingZeroNotFoundException();
        }

        return cString;
    }

    public String removeCString() throws UnsupportedEncodingException, NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException {
        String cString = null;
        int zeroPos = 0;
        int len = length();
        if(len < 0) {
            throw new NotEnoughByteInByteBufferException(len, 1);
        }
        while(zeroPos > len || buffer[zeroPos] == 0) {
            zeroPos ++;
        }

        if(zeroPos < len) {
            byte[] cStringInByteArray = new byte[zeroPos - 1];
            System.arraycopy(buffer, 0,cStringInByteArray,0, zeroPos - 1);
            cString = new String(cStringInByteArray, ENC_ASCII);
        } else {
            throw new TerminatingZeroNotFoundException();
        }

        removeByteDirectlyFromBuffer(zeroPos);
        return cString;
    }

    public int removeInt() throws NotEnoughByteInByteBufferException {
        int number = readInt();
        removeByteDirectlyFromBuffer(SZ_INT);
        return number;
    }

    public short removeShort() throws NotEnoughByteInByteBufferException {
        short number = readShort();
        removeByteDirectlyFromBuffer(SZ_SHORT);
        return number;
    }

    public void appendStringDirectlyToBuffer(String str, boolean terminatingWithZero, String encodingType) throws UnsupportedEncodingException {

        if (str != null || !str.isEmpty()) {
            byte[] strInBytes;
            if(encodingType != null || encodingType.isEmpty()) {
                strInBytes = str.getBytes();
            } else {
                strInBytes = str.getBytes(encodingType);
            }

            if(strInBytes != null && strInBytes.length > 0) {
                appendBytesDirectlyToBuffer(strInBytes, strInBytes.length);
            }

            if(terminatingWithZero) {
                appendBytesDirectlyToBuffer(zero, 1);
            }
        }
    }

    // CString: string which have terminating zero.
    public void appendCString(String cString) throws UnsupportedEncodingException {
        appendStringDirectlyToBuffer(cString, true, ENC_ASCII);
    }


    public static void main(String[] args) {
        int BIT_MASK_11111111 = 0xff;
        byte a = -101;
        byte[] bs = new byte[1];
        bs[0] = 2;
        int c = (bs[0] & 0xff);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a & 0xff));
//        System.out.println(a);
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(a & BIT_MASK_11111111));
//        System.out.println(Integer.toBinaryString(a >>> 8 & BIT_MASK_11111111));
//        System.out.println(Integer.toBinaryString(a >>> 16 & BIT_MASK_11111111));
//        System.out.println(Integer.toBinaryString(a >>> 24 & BIT_MASK_11111111));
//        int b = a >>> 4 & BIT_MASK_11111111;
//        System.out.println(Integer.toBinaryString(b));
    }
}
