package nl.elec332.lib.java.network;


import java.io.IOException;
import java.net.*;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/**
 * Created by Elec332 on 14-1-2020
 */
public class MulticastHelper {

    public static final String DEFAULT_MULTICAST_ADDRESS = "226.1.3.98";

    public static void startMulticastClient(int port, BooleanSupplier isOn, Consumer<DatagramPacket> onReceived) {
        new Thread(() -> {

            try {
                MulticastSocket socket = new MulticastSocket(port);
                socket.setSoTimeout(5000);
                socket.joinGroup(InetAddress.getByName(DEFAULT_MULTICAST_ADDRESS));
                byte[] kCache = new byte[1024]; //1KB cache
                while (isOn.getAsBoolean()) {
                    DatagramPacket datagrampacket = new DatagramPacket(kCache, kCache.length);

                    try {
                        socket.receive(datagrampacket);
                    } catch (SocketTimeoutException var5) {
                        continue;
                    } catch (IOException ioexception) {
                        break;
                    }

                    onReceived.accept(datagrampacket);

                }
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }

        }).start();
    }

    public static void startMulticastSender(int port, byte[] data, BooleanSupplier isOn) {
        new Thread(() -> {
            byte[] abyte = new byte[data.length];
            System.arraycopy(data, 0, abyte, 0, data.length);
            try {
                DatagramSocket socket = new DatagramSocket();

                while (isOn.getAsBoolean()) {
                    try {
                        InetAddress inetaddress = InetAddress.getByName(DEFAULT_MULTICAST_ADDRESS);
                        DatagramPacket datagrampacket = new DatagramPacket(abyte, abyte.length, inetaddress, port);
                        socket.send(datagrampacket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException var5) {
                        //
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
