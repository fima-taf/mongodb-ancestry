package io.fimataf.ancestry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author fima
 * Created on: 20/01/2021
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

    /**
     * Will return true even if only ONE field has this annotation.
     * @param obj
     * @param annotation
     * @return
     */
    public static boolean isFieldsWithAnnotationExists (Object obj, Class<? extends Annotation> annotation) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation)) return true;
        }
        return false;
    }

}
