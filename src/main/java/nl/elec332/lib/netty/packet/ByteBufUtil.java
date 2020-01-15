package nl.elec332.lib.netty.packet;

import io.netty.buffer.ByteBuf;

/**
 * Created by Elec332 on 5-1-2017.
 */
public class ByteBufUtil {

    public static int readVarInt(ByteBuf buf){
        int i = 0;
        int j = 0;
        while (true) {
            byte b0 = buf.readByte();
            i |= (b0 & 127) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
            if ((b0 & 128) != 128) {
                break;
            }
        }
        return i;
    }

    public static ByteBuf writeVarIntToBuffer(ByteBuf buf, int input) {
        while ((input & -128) != 0) {
            buf.writeByte(input & 127 | 128);
            input >>>= 7;
        }
        buf.writeByte(input);
        return buf;
    }

    public static int getVarIntSize(int input) {
        for (int i = 1; i < 5; ++i) {
            if ((input & -1 << i * 7) == 0) {
                return i;
            }
        }

        return 5;
    }

}
