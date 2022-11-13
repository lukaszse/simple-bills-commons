package pl.com.seremak.simplebills.commons.utils;

import java.lang.reflect.Field;

public class ReflectionsUtils {

    public static Object getFieldValue(final Field field, final Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (final IllegalAccessException e) {
            return null;
        }
    }
}
