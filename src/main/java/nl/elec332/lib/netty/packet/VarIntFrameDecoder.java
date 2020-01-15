package nl.elec332.lib.netty.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class VarIntFrameDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        byte[] abyte = new byte[3];
        for (int i = 0; i < abyte.length; ++i) {

            if (!in.isReadable()) {
                in.resetReaderIndex();
                return;
            }

            abyte[i] = in.readByte();

            if (abyte[i] >= 0) {
                ByteBuf packetbuffer = Unpooled.wrappedBuffer(abyte);
                try {
                    int j = ByteBufUtil.readVarInt(packetbuffer);

                    if (in.readableBytes() >= j) {
                        out.add(in.readBytes(j));
                        return;
                    }

                    in.resetReaderIndex();
                } finally {
                    packetbuffer.release();
                }

                return;
            }
        }

        throw new CorruptedFrameException("length wider than 21-bit");
    }

}
