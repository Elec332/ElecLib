package nl.elec332.lib.netty.packet;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Elec332 on 5-1-2017.
 */
public interface IPacket<N> {

    public void readPacketData(ByteBuf in) throws IOException;

    public void writePacketData(ByteBuf out) throws IOException;

    public void processPacket(N handler);

}
