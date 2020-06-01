package nl.elec332.lib.java.tree;

import nl.elec332.lib.java.util.IMappedObject;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Elec332 on 29-4-2020
 */
public interface INamedTreePart extends IMappedObject {

    INamedTreeSpec getSpec();

    INamedTreePart getImmutable();

    boolean hasParent();

    INamedTreePart getParent();

    String getLastModifiedObject();

    Map<String, Object> asMap();

    @Override
    default void map(BiConsumer<String, Object> map) {
        asMap().forEach(map);
    }

    void put(String name, Object obj);

    Object get(String name);

    <T> T get(String name, Class<T> type);

}
