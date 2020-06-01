package nl.elec332.lib.java.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Elec332 on 29-4-2020
 */
public class NamedTree implements INamedTreePart {

    public NamedTree() {
        this(null);
    }

    public NamedTree(INamedTreeSpec spec) {
        this(spec, new HashMap<>());
    }

    private NamedTree(INamedTreeSpec spec, Map<String, Object> map) {
        this.spec = spec;
        this.backedMap = map;
        this.backedMap_ = Collections.unmodifiableMap(this.backedMap);
    }

    private final INamedTreeSpec spec;
    private final Map<String, Object> backedMap, backedMap_;
    private INamedTreePart parent, immutable;
    private String lmo;

    @Override
    public INamedTreePart getImmutable() {
        if (immutable == null) {
            NamedTree ret = new NamedTree(spec, backedMap_) {

                @Override
                public String getLastModifiedObject() {
                    return NamedTree.this.getLastModifiedObject();
                }

            };
            ret.parent = parent == null ? null : parent.getImmutable();
            immutable = ret;
        }
        return immutable;
    }

    @Override
    public INamedTreeSpec getSpec() {
        return spec;
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public INamedTreePart getParent() {
        return parent;
    }

    @Override
    public String getLastModifiedObject() {
        return lmo;
    }

    @Override
    public Map<String, Object> asMap() {
        return this.backedMap_;
    }

    @Override //My eyes...
    public void put(String name, Object obj) {
        Objects.requireNonNull(name);
        if (spec == null) {
            this.backedMap.put(name, obj);
        } else {
            if (obj instanceof INamedTreePart) {
                if (spec.getSubTrees().containsKey(name)) {
                    if (spec.getSubTrees().get(name).equals(((INamedTreePart) obj).getSpec())) {
                        if (obj instanceof NamedTree) {
                            ((NamedTree) obj).parent = this;
                        }
                        this.backedMap.put(name, obj);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } else if (spec.getEntries().containsKey(name)) {
                if (spec.getEntries().get(name).isAssignableFrom(obj.getClass())) {
                    this.backedMap.put(name, obj);
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        this.lmo = name;
    }

    @Override
    public Object get(String name) {
        return this.backedMap.get(name);
    }

    @Override
    public <T> T get(String name, Class<T> type) {
        Object ret = this.backedMap.get(name);
        if (spec == null) {
            return type.cast(ret);
        } else {
            Class<?> ref = spec.getEntries().get(name);
            if (ref != null && type.isAssignableFrom(ref)) {
                return type.cast(ret);
            } else if (spec.getSubTrees().containsKey(name) && type.isAssignableFrom(INamedTreePart.class)) {
                return type.cast(ret);
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "NamedTree" + backedMap;
    }

}
