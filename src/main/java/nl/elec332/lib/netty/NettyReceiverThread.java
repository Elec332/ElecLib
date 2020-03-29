package nl.elec332.lib.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by Elec332 on 6-3-2017.
 */
public class NettyReceiverThread extends Thread {

    public NettyReceiverThread(String ip, int port, IStartable<?, ?> startable) {
        this(new InetSocketAddress(ip, port), startable);
    }

    public NettyReceiverThread(InetSocketAddress address, IStartable<?, ?> startable) {
        this.address = address;
        this.startable = startable;
    }

    private final IStartable<?, ?> startable;
    private final InetSocketAddress address;
    private Channel channel;
    private EventLoopGroup group = new NioEventLoopGroup();

    @Override
    public void run() {
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(startable.getPacketInitializer().get());

            // Start the client.
            ChannelFuture f = b.connect(address).sync();

            channel = f.channel();
            startable.onNettyThreadConnected();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            try { //We cannot allow a crash here, the group must be shut down!
                startable.onNettyExceptionCaught(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
        startable.onNettyThreadShutdown();
    }

    public Channel getChannel() {
        return channel;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

}
