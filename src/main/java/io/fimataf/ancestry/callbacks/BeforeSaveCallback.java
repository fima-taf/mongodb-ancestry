package io.fimataf.ancestry.callbacks;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.annotations.AncestryActions;
import io.fimataf.ancestry.annotations.Parent;
import io.fimataf.ancestry.entities.CustomRelative;
import io.fimataf.ancestry.exceptions.AncestryMongodbException;
import io.fimataf.ancestry.exceptions.NoParentFoundException;
import io.fimataf.ancestry.repositories.DBActions;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fima
 * Created on: 20/01/2021
 */
public class BeforeSaveCallback extends AbstractSaveCallback {

    private String childFieldName;

    public BeforeSaveCallback (DBActions dbActions, String collectionName, Document document, Object source) {
        super(dbActions, collectionName, document, source);
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(Child.class)) {
            childFieldName = AncestryUtils.getDocumentFieldName(field);
            removeChildFromDocument();
            Object child = field.get(source);
            if (!field.getAnnotation(Child.class).linkToChild()) {
                checkChild(child, field);
            }
        } else if (field.isAnnotationPresent(Parent.class)) {
            checkParent(field);
        }
    }

    private void checkParent(Field field) throws IllegalAccessException {
        document.remove(AncestryUtils.getDocumentFieldName(field));
        if (!checkIfParentIsLinkToChild(field)) {
            checkIfChildLinkedWithOtherParent(field);
            Object parent = field.get(source);
            Object childId = AncestryUtils.getEntityIdValue(source);
            if (childId != null && parent != null) {
                Object newParent = instanciateNewParentObject(parent, instanciateNewChildObject(source));
                dbActions.saveEntity(newParent);
            } else if (childId == null && parent != null) {
                throw new AncestryMongodbException("Cannot replace parent of a child without ID. First Create the child, and then replace the parent");
            }
        } else {
            saveChildWithParent(field.get(source), AncestryUtils.getDocumentFieldName(field));
        }
    }

    private void removeChildFromDocument() {
        document.remove(childFieldName);
    }

    private void checkChild(Object child, Field parentField) throws IllegalAccessException {
        List<CustomRelative> childList = checkIfChildIdExistsOnParent(childFieldName);
        if (childList.isEmpty()) {
            if (child != null) {
                saveChild(child, null, parentField);
            }
        } else if (childList.size() == 1) {
            String existingChildId = childList.get(0).getRelativeId();
            if (child != null) {
                String newChildId = getIdFromEntity(child);
                if (newChildId != null && newChildId.equals(existingChildId)) {
                    saveChild(child, existingChildId, parentField);
                    return;
                } else {
                    saveChild(child, null, parentField);
                }
            }
            if (parentField.getAnnotation(Child.class).onChildChange() == AncestryActions.DELETE) {
                removeChildEntity(existingChildId, parentField.getType());
            }
        }
    }

    private void checkIfChildLinkedWithOtherParent (Field field) throws IllegalAccessException {
        String childFieldNameInParent = getChildFieldNameInParent(field);
        List<CustomRelative> parents = checkIfChildExistsOnOtherParents(childFieldNameInParent, getCollectionNameFromEntity(field.getType()));
        if (parents != null && parents.size() > 0) {
            dbActions.updateFields(AncestryUtils.generateAncestryIdFieldName(childFieldNameInParent),
                    parents.stream().map(CustomRelative::getRelativeId).collect(Collectors.toList()),
                    field.getType());
        }
    }

    private String getChildFieldNameInParent (Field parentField) {
        for (Field field : parentField.getType().getDeclaredFields()) {
            if (field.isAnnotationPresent(Child.class)) {
                return AncestryUtils.getDocumentFieldName(field);
            }
        }
        return null;
    }

    private void saveChild (Object child, String existingId, Field parentField) throws IllegalAccessException {
        Object savedChild = dbActions.saveEntity(child);
        addChildToDocument(parentField, existingId != null ? existingId : getIdFromEntity(savedChild));
    }

    private void saveChildWithParent(Object parent, String parentDocName) throws IllegalAccessException {
        if (parent != null) {
            Object parentId = getIdFromEntity(parent);
            if (parentId == null) {
                throw new NoParentFoundException("Parent's id is null. First create the parent and then link it to the child.");
            }
            document.put(AncestryUtils.generateAncestryIdFieldName(parentDocName), parentId);
        }
    }

    private void removeChildEntity (String existingChildId, Class<?> clazz) {
        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(existingChildId));
        dbActions.removeByQueryAndClass(q, clazz);
    }

    private boolean checkIfParentIsLinkToChild(Field parentField) {
        for (Field field : parentField.getType().getDeclaredFields()) {
            if (field.isAnnotationPresent(Child.class) && field.getAnnotation(Child.class).linkToChild()) return true;
        }
        return false;
    }

}
