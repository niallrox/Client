import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Receiver {
    private SocketAddress socketAddress;

    public Object receive(DatagramChannel dc) throws IOException, ClassNotFoundException {

        byte[] sendbuf = new byte[5000];
        ByteBuffer byteBuffer = ByteBuffer.wrap(sendbuf);
        byteBuffer.clear();

        socketAddress = dc.receive(byteBuffer);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sendbuf);
             ObjectInputStream fromServer = new ObjectInputStream(byteArrayInputStream)) {
            Object object = fromServer.readObject();
            return object;
        }

    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }
}
