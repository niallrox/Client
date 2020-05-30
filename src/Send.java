import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class Send {
    DatagramSocket datagramSocket;
    SocketAddress inetAddress;

    public Send(DatagramSocket socket,SocketAddress inetAddress) {
        this.datagramSocket=socket;
        this.inetAddress=inetAddress;
    }



    public void sendobj (Object object) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)){
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte [] sendbuf = byteArrayOutputStream.toByteArray();
        DatagramPacket datagramPacket =new DatagramPacket(sendbuf,sendbuf.length, inetAddress);
        datagramSocket.send(datagramPacket);
    }}
}
