import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;


public class Send {
    DatagramChannel datagramChannel;

    public Send(DatagramChannel datagramChannel) {
        this.datagramChannel = datagramChannel;
    }


    public void sendobj(Object object, SocketAddress address) throws IOException {
        try (ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayStream)) {
            outputStream.writeObject(object);
            outputStream.flush();
            byte[] sendbuf = byteArrayStream.toByteArray();
            ByteBuffer buffer = ByteBuffer.wrap(sendbuf);
            buffer.clear();
            datagramChannel.send(buffer, address);
        }
    }
}
