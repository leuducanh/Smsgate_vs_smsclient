package brandname.smpp_protocol.socket_comunication;



import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCP_IPConnection {


    public static void main(String[] args) throws IOException {

        ServerSocketFactory serverSocketFactory = ServerSocketFactory.getDefault();

        ServerSocket socket = serverSocketFactory.createServerSocket();
        socket.bind( new java.net.InetSocketAddress("127.0.0.1", 8080) );
        boolean succ = false;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        int size = 2*1024;
        while(!succ) {
            Socket socketclient = socket.accept();
            if(socketclient != null) {
                bufferedInputStream = new BufferedInputStream(socketclient.getInputStream(), size);
                bufferedOutputStream = new BufferedOutputStream(socketclient.getOutputStream(), size);
                succ=true;
            }
        }

        byte[] buffer = new byte[4*1024];

        while(true) {
            int readedbyte = bufferedInputStream.read(buffer,0, buffer.length);
            if(readedbyte > 0) {
                int b = 0;
                String str = "";
                for(byte bb:buffer) {
//                    System.out.println(bb + "");
//                    System.out.println("--");

                }
                System.out.println(new String(buffer, StandardCharsets.UTF_8));
            }

        }

    }
}
