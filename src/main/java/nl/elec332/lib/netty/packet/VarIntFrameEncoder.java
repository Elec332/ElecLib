package nl.elec332.lib.netty.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class VarIntFrameEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int i = msg.readableBytes();
        int j = ByteBufUtil.getVarIntSize(i);

        if (j > 3) {
            throw new IllegalArgumentException("unable to fit " + i + " into " + 3);
        } else {
            out.ensureWritable(j + i);
            ByteBufUtil.writeVarIntToBuffer(out, i);
            out.writeBytes(msg, msg.readerIndex(), i);
        }
    }

}
