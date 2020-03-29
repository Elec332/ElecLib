package nl.elec332.lib.netty.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import nl.elec332.lib.netty.IDefaultStartable;
import nl.elec332.lib.netty.INetworkHandler;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class PacketEncoder<N extends INetworkHandler> extends MessageToByteEncoder<IPacket<?>> {

    public PacketEncoder(IDefaultStartable<N> startable) {
        this.startable = startable;
    }

    private IDefaultStartable<N> startable;

    @Override
    protected void encode(ChannelHandlerContext ctx, IPacket<?> msg, ByteBuf out) throws Exception {
        if (!canSend(startable, ctx, msg)) {
            return;
        }
        ByteBufUtil.writeVarIntToBuffer(out, startable.getPacketType(msg));
        msg.writePacketData(out);
    }

    protected boolean canSend(IDefaultStartable<N> startable, ChannelHandlerContext ctx, IPacket<?> msg) {
        return true;
    }

}
