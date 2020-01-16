package nl.elec332.lib.java.util.function;

import javax.annotation.Nullable;
import java.util.function.Function;

/**
 * Created by Elec332 on 16-1-2020
 */
public class FunctionHelper {

    public static <T, S> Function<S, T> cast(final Class<T> type) {
        return o -> cast(o, type);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object o, Class<T> type) {
        if (type.isInstance(o)) {
            return (T) o;
        }
        return null;
    }

}
