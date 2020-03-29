package nl.elec332.lib.netty;

import io.netty.channel.ChannelHandlerContext;
import nl.elec332.lib.netty.packets.PacketShutDown;

/**
 * Created by Elec332 on 5-1-2017.
 */
public abstract class AbstractNetworkHandler<S extends IDefaultStartable<? extends INetworkHandler>> implements INetworkHandler {

    public AbstractNetworkHandler(S startable, ChannelHandlerContext ctx) {
        this.startable = startable;
        this.ctx = ctx;
    }

    protected final S startable;
    protected final ChannelHandlerContext ctx;

    @Override
    public void processShutdownPacket(PacketShutDown packet) {
        System.out.println("Closing connection with: " + ctx.channel());
        ctx.isRemoved();
    }

}
