package io.fimataf.ancestry.callbacks;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.annotations.AncestryActions;
import io.fimataf.ancestry.annotations.Parent;
import io.fimataf.ancestry.repositories.DBActions;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.bson.Document;
import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author fima
 * Created on: 21/01/2021
 */
public class AfterSaveCallback extends AbstractSaveCallback {

    public AfterSaveCallback (DBActions dbActions, String collectionName, Document document, Object source) {
        super(dbActions, collectionName, document, source);
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(Child.class)) {
            removeAncestryChildFromDocument(field);
            addChildToDocument(field, null);
            if (field.getAnnotation(Child.class).linkToChild()) {
                checkChild(field);
            }
        }
    }

    private void checkChild(Field field) throws IllegalAccessException {
        Object child = field.get(source);
        List<Object> existingChildrenList = (List<Object>) checkIfChildContainsParentId(getParentFieldNameInChild(field), field.getType());
        if (existingChildrenList.isEmpty()) {
            if (child != null) {
                saveChild(child);
            }
        } else if (existingChildrenList.size() == 1) {
            String existingChildId = AncestryUtils.getEntityIdValue(existingChildrenList.get(0)).toString();
            if (child != null) {
                if (existingChildId.equals(AncestryUtils.getEntityIdValue(child))) {
                    saveChild(child);
                    return;
                }
                saveChild(child);
            }
            if (field.getAnnotation(Child.class).onChildChange() == AncestryActions.DELETE) {
                dbActions.removeEntity(existingChildrenList.get(0));
            } else {
                removeParentIdFromChild(existingChildrenList.get(0));
            }
        }
    }

    private void saveChild (Object child) throws IllegalAccessException {
        for (Field field : child.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Parent.class)) {
                ReflectionUtils.makeAccessible(field);
                field.set(child, instanciateNewParentObject(source, null));
                dbActions.saveEntity(child);
            }
        }
    }

    private String getParentFieldNameInChild (Field parentField) {
        for (Field field : parentField.getType().getDeclaredFields()) {
            if (field.isAnnotationPresent(Parent.class)) {
                return AncestryUtils.getDocumentFieldName(field);
            }
        }
        return null;
    }

    private void removeAncestryChildFromDocument (Field field) {
        document.remove(AncestryUtils.generateAncestryIdFieldName(AncestryUtils.getDocumentFieldName(field)));
    }

    private void removeParentIdFromChild (Object child) throws IllegalAccessException {
        for (Field field : child.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Parent.class)) {
                ReflectionUtils.makeAccessible(field);
                field.set(child, null);
            }
        }
        dbActions.saveEntity(child);
    }

}
