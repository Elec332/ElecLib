package nl.elec332.lib.java.tree;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Elec332 on 29-4-2020
 */
@Deprecated
public interface INamedTreeSpec {

    Map<String, Class<?>> getEntries();

    Map<String, INamedTreeSpec> getSubTrees();

    interface Builder {

        void addEntry(String name, Class<?> type);

        void addSubtree(String name, Consumer<Builder> subBuilder);

        INamedTreeSpec build();

    }

}
