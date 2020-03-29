package nl.elec332.lib.netty.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import nl.elec332.lib.netty.IDefaultStartable;

import java.util.List;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class PacketDecoder extends ByteToMessageDecoder {

    public PacketDecoder(IDefaultStartable<?> startable) {
        this.startable = startable;
    }

    private final IDefaultStartable<?> startable;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in instanceof EmptyByteBuf) { //connection probably closed
            System.out.println("Found empty ByteBuf on connection with: " + ctx.channel().localAddress() + " Closing connection...");
            ctx.close();
            return;
        }
        int i = ByteBufUtil.readVarInt(in);
        IPacket<?> packet = startable.createPacketFromType(i);
        packet.readPacketData(in);
        out.add(packet);
    }

}
