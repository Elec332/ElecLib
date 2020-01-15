package nl.elec332.lib.java.io;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by Elec332 on 3-9-2019
 */
public class VersionedReader<T> implements Function<Integer, Function<IByteArrayDataInputStream, T>> {

    public VersionedReader(Function<IByteArrayDataInputStream, T> defaultReader) {
        this.defaultReader = defaultReader;
        this.versionMap = Maps.newHashMap();
    }

    private final Function<IByteArrayDataInputStream, T> defaultReader;
    private final Map<Integer, Function<IByteArrayDataInputStream, T>> versionMap;

    public void registerVersion(int version, Function<IByteArrayDataInputStream, T> reader) {
        if (version < 1) {
            throw new IllegalArgumentException();
        }
        this.versionMap.put(version, reader);
    }

    @Override
    public Function<IByteArrayDataInputStream, T> apply(Integer i) {
        return this.versionMap.getOrDefault(i, defaultReader);
    }

}
