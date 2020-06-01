package nl.elec332.lib.java.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Elec332 on 29-4-2020
 */
@Deprecated
public class NamedTreeSpec implements INamedTreeSpec {

    public static INamedTreeSpec.Builder builder() {
        return new Builder();
    }

    private NamedTreeSpec(Map<String, Class<?>> entries, Map<String, INamedTreeSpec> subTrees) {
        this.entries = Collections.unmodifiableMap(entries);
        this.subTrees = Collections.unmodifiableMap(subTrees);
    }

    private final Map<String, Class<?>> entries;
    private final Map<String, INamedTreeSpec> subTrees;

    @Override
    public Map<String, Class<?>> getEntries() {
        return entries;
    }

    @Override
    public Map<String, INamedTreeSpec> getSubTrees() {
        return subTrees;
    }

    private static class Builder implements INamedTreeSpec.Builder {

        private Builder() {
            this.entries = new HashMap<>();
            this.subTrees = new HashMap<>();
        }

        private final Map<String, Class<?>> entries;
        private final Map<String, INamedTreeSpec> subTrees;

        @Override
        public void addEntry(String name, Class<?> type) {
            if (INamedTreeSpec.class.isAssignableFrom(type) || INamedTreeSpec.class.isAssignableFrom(type)) {
                throw new UnsupportedOperationException();
            }
            this.entries.put(name, type);
        }

        @Override
        public void addSubtree(String name, Consumer<INamedTreeSpec.Builder> subBuilder) {
            INamedTreeSpec.Builder sub = new Builder();
            subBuilder.accept(sub);
            this.subTrees.put(name, sub.build());
        }

        @Override
        public INamedTreeSpec build() {
            return new NamedTreeSpec(this.entries, this.subTrees);
        }

    }

    // Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedTreeSpec that = (NamedTreeSpec) o;
        return entries.equals(that.entries) &&
                subTrees.equals(that.subTrees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries, subTrees);
    }

}
