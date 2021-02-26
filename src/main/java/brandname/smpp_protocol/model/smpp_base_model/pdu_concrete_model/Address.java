package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model;

import brandname.smpp_protocol.exceptions.InvalidPDUException;
import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.ByteData;
import brandname.smpp_protocol.model.util.Constants;

import java.io.UnsupportedEncodingException;

public class Address extends ByteData {

    private byte addr_ton;
    private byte addr_npi;
    private String addr;



//    public static void main(String[] args) throws UnsupportedEncodingException, WrongLengthOfStringException {
//        System.out.println(10*20+"test");
//        System.out.println("test"+10*20);
//        String s = "ababbabbbbaaababbbbbaabbabbbbabbabbabbbbabbbbaaaaaabaaababbaaaaaaaabbabababbaabbbbaabbbaaabababbaaababaaababbbbaababaaaaaaaabbabbbbbaabaabababaababbbabaaaaaaabb";
//        byte[] a = s.getBytes("ASCII");
//        System.out.println(a.length);
//
//        SubmitSM submit = new SubmitSM();
//
//        submit.setShortMessage(new String(a, "X-Gsm7Bit"));
//        submit.setDataCoding((byte) 0x00);
//
//        System.out.println("1- " + submit.getShortMessage());
//        System.out.println("2- " + submit.getShortMessage().getBytes("ASCII"));
//        System.out.println(a.length);
//        System.out.println(a.length * 8);
//
//
//        for(byte by : a) {
//
//        }
//        String str  = new String(a, "ASCII");
//        System.out.println(str);
//
//        byte[] q1 = s.getBytes(Charset.forName("UTF-8"));
//        byte[] q2 = s.getBytes(Charset.forName("UTF-16BE"));
//
//        System.out.println(new String(a,"X-Gsm7Bit"));
//        System.out.println(q1.length);
//        System.out.println(q2.length);
//
////        int msgLength = 160;
////        int nNumberOfMsg = (msgLength - 1) / 135 + 1;
////        System.out.println(nNumberOfMsg);
//
//    }

    public Address() {
        addr_ton = Constants.TonData.Unknown;
        addr_npi = Constants.TonData.Unknown;
        addr = Constants.DefaultPDUField.DEFAULT_EMPTY_FIELD;
    }

    @Override
    public void setData(ByteBuffer buffer) throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException, InvalidPDUException, UnsupportedEncodingException {
        addr_ton = buffer.removeByte();
        addr_npi = buffer.removeByte();
        addr = buffer.removeCString();
    }

    @Override
    public ByteBuffer getData() throws NotEnoughByteInByteBufferException {
        ByteBuffer byteBuffer = new ByteBuffer();
        byteBuffer.appendByte(addr_ton);
        byteBuffer.appendByte(addr_npi);
        byteBuffer.appendCString(addr);
        return byteBuffer;
    }

    public int getAddr_ton() {
        return addr_ton;
    }


    public int getAddr_npi() {
        return addr_npi;
    }


    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
