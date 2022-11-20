package pl.com.seremak.simplebills.commons.utils;

import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;
import static pl.com.seremak.simplebills.commons.utils.ReflectionsUtils.getFieldValue;
import static pl.com.seremak.simplebills.commons.utils.VersionedEntityUtils.updateMetadata;

public class MongoQueryHelper {

    public static <T> Update preparePartialUpdateQuery(final Object object, final Class<T> clazz) {
        final Update update = new Update();
        getAllClassFields(clazz).stream()
                .filter(field -> nonNull(getFieldValue(field, object)))
                .forEach(field -> update.set(field.getName(), getFieldValue(field, object)));
        return updateMetadata(update);
    }

    private static <T> List<Field> getAllClassFields(Class<T> clazz) {
        final List<Field> classFields = Arrays.asList(clazz.getDeclaredFields());
        if(nonNull(clazz.getSuperclass())) {
            classFields.addAll(getAllClassFields(clazz.getSuperclass()));
        }
        return classFields;
    }
}
