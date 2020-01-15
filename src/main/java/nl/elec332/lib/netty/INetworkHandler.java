package nl.elec332.lib.netty;

import nl.elec332.lib.netty.packets.PacketShutDown;

/**
 * Created by Elec332 on 5-1-2017.
 */
public interface INetworkHandler {

    public void processShutdownPacket(PacketShutDown packet);

}
