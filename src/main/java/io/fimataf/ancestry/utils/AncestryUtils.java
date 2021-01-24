package io.fimataf.ancestry.utils;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author fima
 * Created on: 22/01/2021
 */
public class AncestryUtils {

    public static final String DEFAULT_ID_FIELD_NAME = "_id";

    public static String generateAncestryIdFieldName(String baseName) {
        return "_" + baseName + "Id";
    }

    public static String getDocumentFieldName (Field field) {
        if (field.isAnnotationPresent(org.springframework.data.mongodb.core.mapping.Field.class)) {
            return field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class).name();
        } else {
            return field.getName();
        }
    }

    public static Object getEntityIdValue(Object entity) throws IllegalAccessException {
        for (Field field: entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                ReflectionUtils.makeAccessible(field);
                return field.get(entity);
            }
        }
        return "";
    }

}
