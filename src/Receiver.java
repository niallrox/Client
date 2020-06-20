import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;


public class Receiver {
    private DatagramChannel datagramChannel;

    public Receiver(DatagramChannel datagramChannel) {
        this.datagramChannel = datagramChannel;
    }

    public Object receiveob(byte[] codedPacket) {
        ByteBuffer buffer = ByteBuffer.wrap(codedPacket);
        buffer.clear();
        try {
            SocketAddress address;
            do {
                address = datagramChannel.receive(buffer);
            } while (address == null);

            ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(codedPacket);
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayStream);
            return inputStream.readObject();
        }catch (PortUnreachableException e){
            System.out.println("Порт недоступен");
            System.exit(1);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
