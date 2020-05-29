import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Send {
    public void send(Object object, SocketAddress address, DatagramChannel datagramChannel) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            byte[] sendbuf = byteArrayOutputStream.toByteArray();
            ByteBuffer byteBuffer = ByteBuffer.wrap(sendbuf);
            datagramChannel.send(byteBuffer, address);
        }
    }
}