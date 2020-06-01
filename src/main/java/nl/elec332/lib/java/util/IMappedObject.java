package nl.elec332.lib.java.util;

import java.util.function.BiConsumer;

/**
 * Created by Elec332 on 24-4-2020
 */
public interface IMappedObject {

    void map(BiConsumer<String, Object> map);

}
