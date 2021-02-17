package brandname.smpp_protocol.model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;

import java.io.UnsupportedEncodingException;

public class ByteBuffer {


    public static final byte SZ_BYTE = 1;
    public static final byte SZ_SHORT = 2;
    public static final byte SZ_INT = 4;

    private static final String ENC_ASCII = "ASCII";

    private static final int BIT_MASK_11111111 = 0xff;

    private byte[] buffer;

    private static byte[] zero;

    static {
        zero = new byte[1];
        zero[0] = 0;
    }

    public ByteBuffer() {
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public int length() {
        if (buffer == null) {
            return 0;
        } else {
            return buffer.length;
        }
    }

    public void appendOtherByteBuffer(ByteBuffer byteBuffer) throws NotEnoughByteInByteBufferException {
        if (byteBuffer == null || byteBuffer.length() <= 0) {
            throw new NotEnoughByteInByteBufferException(0, 1);
        }

        byte[] appendedBuffer = byteBuffer.getItsBuffer();
        this.appendBytesDirectlyToBuffer(appendedBuffer, appendedBuffer.length);

    }

    // todo:
    public ByteBuffer readByteBuffer(int count) throws NotEnoughByteInByteBufferException {
        if (count > length()) {
            throw new NotEnoughByteInByteBufferException(length(), count);
        }
        ByteBuffer readByteBuffer = new ByteBuffer();
        byte[] newByteArray = new byte[count];
        System.arraycopy(buffer, 0, newByteArray, 0, count);
        readByteBuffer.appendBytesDirectlyToBuffer(newByteArray, count);
        return readByteBuffer;
    }


    public byte[] getItsBuffer() {
        return buffer;
    }

    public void appendBytesDirectlyToBuffer(byte[] bytes, int count) {
        int len = length();
        byte[] newBuffer = new byte[len + count];
        if (len > 0) {
            System.arraycopy(buffer, 0, newBuffer, 0, len);
        }
        System.arraycopy(bytes, 0, newBuffer, len, count);
        setBuffer(newBuffer);
    }

    public void appendByte(byte oneByte) {
        byte[] appendedBytes = new byte[1];
        appendBytesDirectlyToBuffer(appendedBytes, 1);
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

    public ByteBuffer readBytes(int numberOfReadBytes) throws NotEnoughByteInByteBufferException {
        ByteBuffer byteBuffer = new ByteBuffer();

        if (buffer.length < numberOfReadBytes) {
            throw new NotEnoughByteInByteBufferException(buffer.length, numberOfReadBytes);
        } else {
            byte[] bytes = new byte[numberOfReadBytes];
            System.arraycopy(buffer, 0, bytes, 0, numberOfReadBytes);
            byteBuffer.appendBytesDirectlyToBuffer(bytes, numberOfReadBytes);
        }
        return byteBuffer;
    }

    public ByteBuffer removeBytes(int numberOfRemovedBytes) throws NotEnoughByteInByteBufferException {
        ByteBuffer byteBuffer = readBytes(numberOfRemovedBytes);
        removeByteDirectlyFromBuffer(numberOfRemovedBytes);
        return byteBuffer;
    }

    public void appendInt(int number) {
        byte[] bytes = new byte[SZ_INT];

        bytes[3] = (byte) (number & 0xff);
        number >>= 4;
        bytes[2] = (byte) (number & 0xff);
        number >>= 4;
        bytes[1] = (byte) (number & 0xff);
        number >>= 4;
        bytes[0] = (byte) (number & 0xff);
        appendBytesDirectlyToBuffer(bytes, SZ_INT);
    }

    public void appendShort(short number) {
        byte[] bytes = new byte[SZ_INT];

        bytes[1] = (byte) (number & 0xff);
        number >>= 4;
        bytes[0] = (byte) (number & 0xff);
        appendBytesDirectlyToBuffer(bytes, SZ_SHORT);
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

    public String readCString() throws
            UnsupportedEncodingException, NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException {
        String cString = null;
        int zeroPos = 0;
        int len = length();
        if (len < 0) {
            throw new NotEnoughByteInByteBufferException(len, 1);
        }
        while (zeroPos > len || buffer[zeroPos] == 0) {
            zeroPos++;
        }

        if (zeroPos < len) {
            byte[] cStringInByteArray = new byte[zeroPos - 1];
            System.arraycopy(buffer, 0, cStringInByteArray, 0, zeroPos - 1);
            cString = new String(cStringInByteArray, ENC_ASCII);
        } else {
            throw new TerminatingZeroNotFoundException();
        }

        return cString;
    }

    public byte readByte() {
        return buffer[0];
    }

    public String removeCString() throws
            UnsupportedEncodingException, NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException {
        String cString = null;
        int zeroPos = 0;
        int len = length();
        if (len < 0) {
            throw new NotEnoughByteInByteBufferException(len, 1);
        }
        while (zeroPos > len || buffer[zeroPos] == 0) {
            zeroPos++;
        }

        if (zeroPos < len) {
            byte[] cStringInByteArray = new byte[zeroPos - 1];
            System.arraycopy(buffer, 0, cStringInByteArray, 0, zeroPos - 1);
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

    public byte removeByte() throws NotEnoughByteInByteBufferException {
        byte byteRead = readByte();
        removeByteDirectlyFromBuffer(1);
        return byteRead;
    }

    public void appendStringDirectlyToBuffer(String str, boolean terminatingWithZero, String encodingType) throws
            UnsupportedEncodingException {

        if (str != null || !str.isEmpty()) {
            byte[] strInBytes;
            if (encodingType != null || encodingType.isEmpty()) {
                strInBytes = str.getBytes();
            } else {
                strInBytes = str.getBytes(encodingType);
            }

            if (strInBytes != null && strInBytes.length > 0) {
                appendBytesDirectlyToBuffer(strInBytes, strInBytes.length);
            }

            if (terminatingWithZero) {
                appendBytesDirectlyToBuffer(zero, 1);
            }
        }
    }

    // CString: string which have terminating zero.
    public void appendCString(String cString) throws UnsupportedEncodingException {
        appendStringDirectlyToBuffer(cString, true, ENC_ASCII);
    }


    public static void main(String[] args) {
//        int BIT_MASK_11111111 = 0xff;
//        byte a = -101;
//        byte[] bs = new byte[1];
//        bs[0] = 2;
//        int c = (bs[0] & 0xff);
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(a & 0xff));
//        int g = 2147483647;
//         Short.MAX_VALUE
//        System.out.println(a);
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(a & BIT_MASK_11111111));
//        System.out.println(Integer.toBinaryString(a >>> 8 & BIT_MASK_11111111));
//        System.out.println(Integer.toBinaryString(a >>> 16 & BIT_MASK_11111111));
//        System.out.println(Integer.toBinaryString(a >>> 24 & BIT_MASK_11111111));
//        int b = a >>> 4 & BIT_MASK_11111111;
//        System.out.println(Integer.toBinaryString(b));


        int a = Short.MAX_VALUE + 100;
        System.out.println(a);
        short s = (short) (Short.MAX_VALUE - a);

        int a1 = Short.MAX_VALUE - s;

        System.out.println(a1);


        System.out.println(Short.MAX_VALUE - Short.MIN_VALUE);
        System.out.println(65536);
    }
}
