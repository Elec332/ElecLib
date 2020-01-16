package nl.elec332.lib.netty.packet;

import nl.elec332.lib.netty.IStartable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class PacketInboundHandler<N> extends SimpleChannelInboundHandler<IPacket<N>> {

    public PacketInboundHandler(IStartable<N, ?> startable){
        this.startable = startable;
    }

    private final IStartable<N, ?> startable;

    private N networkHandler;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        networkHandler = startable.getNetworkHandlerFactory().apply(ctx);
        startable.setNetworkHandler(networkHandler);
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IPacket<N> msg) throws Exception {
        if (canProcess(startable, ctx, msg)) { //Extra filter
            msg.processPacket(networkHandler);
        }
    }

    protected boolean canProcess(IStartable<N, ?> startable, ChannelHandlerContext ctx, IPacket<N> packet){
        return true;
    }

}
