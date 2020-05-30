import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Receiver {
    DatagramSocket datagramSocket;
    public Receiver(DatagramSocket datagramSocket){
        this.datagramSocket=datagramSocket;
    }

    private InetAddress inetAddress;
    private int port;

    


    public Object receiveob(byte[] sendbuf) throws IOException,ClassNotFoundException {
        DatagramPacket datagramPacket = new DatagramPacket(sendbuf,sendbuf.length);
        datagramSocket.receive(datagramPacket);
        inetAddress=datagramPacket.getAddress();
        port=datagramPacket.getPort();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
        ObjectInputStream fromServer = new ObjectInputStream(byteArrayInputStream);
        Object object =fromServer.readObject();
        byteArrayInputStream.close();
        fromServer.close();
        return object;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public int getPort() {
        return port;
    }
}
