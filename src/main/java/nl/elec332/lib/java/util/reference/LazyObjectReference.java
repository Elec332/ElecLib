package nl.elec332.lib.java.util.reference;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

/**
 * Created by Elec332 on 14-1-2020
 */
public class LazyObjectReference<T> extends AbstractLazyObjectReference<T> {

    public LazyObjectReference(Supplier<T> init) {
        this.initializer = init;
    }

    private final Supplier<T> initializer;

    @Nonnull
    @Override
    protected T create() {
        return initializer.get();
    }

}
