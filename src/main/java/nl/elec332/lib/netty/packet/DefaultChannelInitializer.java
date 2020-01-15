package nl.elec332.lib.netty.packet;

import nl.elec332.lib.netty.IDefaultStartable;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class DefaultChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel> {

    public DefaultChannelInitializer(IDefaultStartable<?> startable){
        this.startable = startable;
    }

    protected final IDefaultStartable<?> startable;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast("splitter", new VarIntFrameDecoder());
        p.addLast("decoder", new PacketDecoder(startable));
        p.addLast("prepender", new VarIntFrameEncoder());
        p.addLast("encoder", startable.createPacketEncoder(this));
        p.addLast("packetHandler", startable.createInboundHandler(this));
    }

}
