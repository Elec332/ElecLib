package nl.elec332.lib.netty.packets;

import nl.elec332.lib.netty.INetworkHandler;
import nl.elec332.lib.netty.packet.IPacket;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class PacketShutDown implements IPacket<INetworkHandler> {

    @Override
    public void readPacketData(ByteBuf in) throws IOException {
    }

    @Override
    public void writePacketData(ByteBuf out) throws IOException {
    }

    @Override
    public void processPacket(INetworkHandler handler) {
        handler.processShutdownPacket(this);
    }

}
