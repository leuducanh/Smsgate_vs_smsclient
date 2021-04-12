package brandname.smpp_protocol.socket_comunication;



import org.smpp.pdu.PDUException;
import org.smpp.pdu.SubmitSM;
import org.smpp.util.ByteBuffer;
import org.smpp.util.NotEnoughDataInByteBufferException;
import org.smpp.util.TerminatingZeroNotFoundException;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCP_IPConnection {


    public static void main(String[] args) throws IOException {

//        ServerSocketFactory serverSocketFactory = ServerSocketFactory.getDefault();
//
//        ServerSocket socket = serverSocketFactory.createServerSocket();
//        socket.bind( new java.net.InetSocketAddress("127.0.0.1", 8080) );
//        boolean succ = false;
//        BufferedInputStream bufferedInputStream = null;
//        BufferedOutputStream bufferedOutputStream = null;
//        int size = 2*1024;
//        while(!succ) {
//            Socket socketclient = socket.accept();
//            if(socketclient != null) {
//                bufferedInputStream = new BufferedInputStream(socketclient.getInputStream(), size);
//                bufferedOutputStream = new BufferedOutputStream(socketclient.getOutputStream(), size);
//                succ=true;
//            }
//        }
//
//        byte[] buffer = new byte[4*1024];
//
//        while(true) {
//            int readedbyte = bufferedInputStream.read(buffer,0, buffer.length);
//            if(readedbyte > 0) {
//                int b = 0;
//                String str = "";
//                for(byte bb:buffer) {
////                    System.out.println(bb + "");
////                    System.out.println("--");
//
//                }
//                System.out.println(new String(buffer, StandardCharsets.UTF_8));
//            }
//
//        }


//        new BufferedReader(new InputStreamReader(new ByteArrayInputStream("123".getBytes("UTF-8")) {
//
//            @Override
//            public synchronized int read() {
//                System.err.println("ByteArrayInputStream.read()");
//                return super.read();
//            }
//
//            @Override
//            public synchronized int read(byte[] b, int off, int len) {
//                System.err.println("ByteArrayInputStream.read(..., " + off + ", " + len + ')');
//                return super.read(b, off, len);
//            }
//
//        }, "UTF-8") {
//
//            @Override
//            public int read() throws IOException {
//                System.err.println("InputStreamReader.read()");
//                return super.read();
//            }
//
//            @Override
//            public int read(char[] cbuf, int offset, int length) throws IOException {
//                System.err.println("InputStreamReader.read(..., " + offset + ", " + length + ')');
//                return super.read(cbuf, offset, length);
//            }
//
//        }).read();

        int index = 0;
        int s = 0;
        int e = 0;
        String a = "Tens interesse em fazer novas amizades? Adira ja a nova rede social - MeetMe da Movitel, onde podes conhecer novas pessoas e fazer amizades com pessoas proximas a ti, com os mesmos gostos e interesses por apenas 2 MT por dia . Para te registares va ao campo de  mensagens e envia ON para o 866 ou digita *866# e conheca os teus novos \"Bros\". Tens direito a 3 dias gratis apos  o primeiro registo.";
        byte[] content = getLongText(a.getBytes("ASCII"));
        while (e < content.length) {
            e = s + 140 < content.length ? s + 140 : content.length;
            byte[] smsdata = new byte[e - s];
            System.arraycopy(content, s, smsdata, 0, e - s);
            try {

                SubmitSM submit = new SubmitSM();
                submit.getShortMessage();

                submit.setShortMessageData(new ByteBuffer(smsdata));
                submit.setDataCoding((byte) 0x00);
                submit.setEsmClass((byte) 0x40);

                System.out.println(submit.getShortMessage());
            } catch (RuntimeException | PDUException | NotEnoughDataInByteBufferException | TerminatingZeroNotFoundException ex) {
            }
            s = e;
            index++;
        }



    }

    public static int seqOfMsg=0;
    public static byte[] getLongText(byte[] data) {
//        int nNumberOfMsg = true;
        int msgLength = data.length;
        int nNumberOfMsg = (msgLength - 1) / 135 + 1;
        ByteBuffer buf = new ByteBuffer();
        int index = 0;
        msgLength = data.length;
        ++seqOfMsg;
        if (seqOfMsg >= 255) {
            seqOfMsg = 1;
        }

        for(int i = 0; i < nNumberOfMsg; ++i) {
            int maxByteData = 140;
            buf.appendByte((byte)5);
            buf.appendByte((byte)0);
            buf.appendByte((byte)3);
            buf.appendByte((byte)seqOfMsg);
            buf.appendByte((byte)nNumberOfMsg);
            buf.appendByte((byte)(i + 1));
             maxByteData = maxByteData - 6;
            int availableData = msgLength - index;
            int byteData = availableData > maxByteData ? maxByteData : availableData;
            byte[] b = new byte[byteData];
            System.arraycopy(data, index, b, 0, byteData);
            index += byteData;
            buf.appendBytes(b);
        }

        return buf.getBuffer();
    }
}
