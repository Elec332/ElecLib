package nl.elec332.lib.java.util.reference;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 5-1-2017.
 */
public abstract class AbstractLazyObjectReference<T> extends ObjectReference<T> {

    @Nonnull
    public T get(){
        if (object == null){
            object = create();
        }
        return super.get();
    }

    @Nonnull
    protected abstract T create();

}
