package nl.elec332.lib.netty.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import nl.elec332.lib.java.io.*;

import java.io.IOException;

/**
 * Created by Elec332 on 15-1-2020
 */
public abstract class AbstractDataPacket<T> implements IPacket<T>, IDataSerializable {

    @Override
    public final void readPacketData(ByteBuf in) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteBufInputStream(in));
        dis.readObject(this);
        dis.close();
    }

    @Override
    public final void writePacketData(ByteBuf out) throws IOException {
        DataOutputStream dos = new DataOutputStream(new ByteBufOutputStream(out));
        dos.writeObject(this);
        dos.flush();
        dos.close();
    }

    @Override
    public abstract void writeObject(IByteArrayDataOutputStream stream);

    @Override
    public abstract void readObject(IByteArrayDataInputStream stream);

    @Override
    public abstract void processPacket(T handler);

}
