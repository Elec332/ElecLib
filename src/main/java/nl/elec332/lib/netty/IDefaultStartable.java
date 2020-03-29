package nl.elec332.lib.netty;

import io.netty.channel.SimpleChannelInboundHandler;
import nl.elec332.lib.java.util.reference.AbstractLazyObjectReference;
import nl.elec332.lib.netty.packet.DefaultChannelInitializer;
import nl.elec332.lib.netty.packet.IPacket;
import nl.elec332.lib.netty.packet.PacketEncoder;
import nl.elec332.lib.netty.packet.PacketInboundHandler;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 8-3-2017.
 */
public interface IDefaultStartable<N> extends IStartable<N, DefaultChannelInitializer> {

    @Override
    default public AbstractLazyObjectReference<DefaultChannelInitializer> getPacketInitializer() {
        return new AbstractLazyObjectReference<DefaultChannelInitializer>() {

            @Nonnull
            @Override
            protected DefaultChannelInitializer create() {
                return new DefaultChannelInitializer(IDefaultStartable.this);
            }

        };
    }

    @SuppressWarnings("unchecked")
    default public SimpleChannelInboundHandler<?> createInboundHandler(DefaultChannelInitializer packetInitializer) {
        return new PacketInboundHandler(this);
    }

    @SuppressWarnings("unchecked")
    default public PacketEncoder createPacketEncoder(DefaultChannelInitializer packetInitializer) {
        return new PacketEncoder(this);
    }

    public IPacket<?> createPacketFromType(int type);

    public int getPacketType(IPacket<?> packet);

}
