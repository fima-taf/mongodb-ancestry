package io.fimataf.ancestry.callbacks;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.annotations.Parent;
import org.bson.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author fima
 * Created on: 21/01/2021
 */
public class AfterSaveCallback extends AbstractSaveCallback {

    public AfterSaveCallback (MongoOperations mongoOperations, String collectionName, Document document, Object source) {
        super(mongoOperations, collectionName, document, source);
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(Child.class)) {
            addChildToDocument(field, null);
            if (field.getAnnotation(Child.class).linkToChild()) {
                Object child = field.get(source);
                try {
                    saveChild(child);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveChild (Object child) throws Exception {
        if (child == null) return;
        for (Field field : child.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Parent.class)) {
                ReflectionUtils.makeAccessible(field);
                boolean keepAfterSave = field.getAnnotation(Parent.class).keepAfterSave();
                field.set(child, instanciateNewParentObject(keepAfterSave));
                mongoOperations.save(child);
                if (!keepAfterSave) {
                    field.set(child, null);
                }
            }
        }
    }

    private Object instanciateNewParentObject (boolean fullInstanciation) throws Exception {
        Object newParentInstance = source.getClass().newInstance();
        for (Field field : newParentInstance.getClass().getDeclaredFields()) {
            ReflectionUtils.makeAccessible(field);
            if (field.isAnnotationPresent(Id.class)) {
                field.set(newParentInstance, field.get(source));
            } else if (fullInstanciation && !field.isAnnotationPresent(Child.class)) {
                field.set(newParentInstance, field.get(source));
            }
        }
        return newParentInstance;
    }

}
