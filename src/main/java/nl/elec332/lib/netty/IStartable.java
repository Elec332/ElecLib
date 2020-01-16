package nl.elec332.lib.netty;

import nl.elec332.lib.java.util.reference.AbstractLazyObjectReference;
import nl.elec332.lib.netty.packet.DefaultChannelInitializer;
import io.netty.channel.ChannelHandlerContext;

import java.util.function.Function;

/**
 * Created by Elec332 on 5-1-2017.
 */
public interface IStartable<N, P extends DefaultChannelInitializer> {

    public void start() throws Exception;

    public Function<ChannelHandlerContext, N> getNetworkHandlerFactory();

    default void setNetworkHandler(N networkHandler){
    }

    public AbstractLazyObjectReference<P> getPacketInitializer();

    default public void onNettyThreadShutdown(){
    }

    default public void onNettyThreadConnected(){
    }

    default public void onNettyExceptionCaught(Exception e){
        e.printStackTrace();
    }

}
