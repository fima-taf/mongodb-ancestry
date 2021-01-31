package io.fimataf.ancestry.callbacks;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.annotations.Parent;
import io.fimataf.ancestry.entities.CustomRelative;
import io.fimataf.ancestry.exceptions.AncestryMongodbException;
import io.fimataf.ancestry.repositories.DBActions;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.bson.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author fima
 * Created on: 24/01/2021
 */
public abstract class AbstractSaveCallback implements ReflectionUtils.FieldCallback {

    protected DBActions dbActions;
    protected String collectionName;
    protected Document document;
    protected Object source;

    public AbstractSaveCallback(DBActions dbActions, String collectionName, Document document, Object source) {
        this.dbActions = dbActions;
        this.collectionName = collectionName;
        this.document = document;
        this.source = source;
    }

    @Override
    public abstract void doWith(Field field) throws IllegalArgumentException, IllegalAccessException;

    /**
     * If Id is null it will save the whole object, if Id is present it will save that string only
     * @param field
     * @param id
     * @throws IllegalAccessException
     */
    protected void addChildToDocument (Field field, String id) throws IllegalAccessException {
        String docFieldName = AncestryUtils.getDocumentFieldName(field);
        if (id != null) {
            document.put(AncestryUtils.generateAncestryIdFieldName(docFieldName), id);
        } else {
            Document childDocument = new Document();
            dbActions.convertObjectIntoDocument(field.get(source), childDocument);
            document.put(docFieldName, childDocument);
        }
    }

    protected Object instanciateNewParentObject (Object object, Object includeChild) {
        try {
            Object newParentInstance = object.getClass().newInstance();
            for (Field field : newParentInstance.getClass().getDeclaredFields()) {
                ReflectionUtils.makeAccessible(field);
                if (!field.isAnnotationPresent(Child.class)) {
                    field.set(newParentInstance, field.get(object));
                } else if (includeChild != null && field.isAnnotationPresent(Child.class)) {
                    field.set(newParentInstance, includeChild);
                }
            }
            return newParentInstance;
        } catch (Exception e) {
            throw new AncestryMongodbException("The object " + object.getClass().getName() + " must have any empty constructor");
        }
    }

    protected Object instanciateNewChildObject (Object object) {
        try {
            Object newParentInstance = object.getClass().newInstance();
            for (Field field : newParentInstance.getClass().getDeclaredFields()) {
                ReflectionUtils.makeAccessible(field);
                if (!field.isAnnotationPresent(Parent.class)) {
                    field.set(newParentInstance, field.get(object));
                }
            }
            return newParentInstance;
        } catch (Exception e) {
            throw new AncestryMongodbException("The object " + object.getClass().getName() + " must have any empty constructor");
        }
    }

    protected String getIdFromEntity (Object entity) throws IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                ReflectionUtils.makeAccessible(field);
                Object id = field.get(entity);
                if (id != null) {
                    return field.get(entity).toString();
                } else break;
            }
        }
        return null;
    }

    protected List<CustomRelative> checkIfChildIdExistsOnParent(String fieldName) throws IllegalAccessException {
        MatchOperation matchId = Aggregation.match(new Criteria(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(AncestryUtils.getEntityIdValue(source)));
        ProjectionOperation excludeId = Aggregation.project(AncestryUtils.generateAncestryIdFieldName(fieldName)).andExclude(AncestryUtils.DEFAULT_ID_FIELD_NAME);
        ProjectionOperation renamedRelativeIdField = Aggregation.project().and(AncestryUtils.generateAncestryIdFieldName(fieldName)).as(CustomRelative.RELATIVE_ID);

        return checkIfRelativeIdExists(collectionName, Aggregation.newAggregation(matchId, excludeId, renamedRelativeIdField));
    }

    protected List<?> checkIfChildContainsParentId(String fieldName, Class<?> clazz) throws IllegalAccessException {
        Query q = new Query(new Criteria(AncestryUtils.generateAncestryIdFieldName(fieldName)).is(AncestryUtils.getEntityIdValue(source)));
        return dbActions.findWithQueryAndClass(q, clazz);
    }

    private List<CustomRelative> checkIfRelativeIdExists(String collectionName, Aggregation aggregation) {
        return dbActions.findWithAggregation(aggregation, collectionName, CustomRelative.class);
    }

    protected List<CustomRelative> checkIfChildExistsOnOtherParents (String fieldName, String parentCollectionName) throws IllegalAccessException {
        MatchOperation matchId = Aggregation.match(new Criteria(AncestryUtils.generateAncestryIdFieldName(fieldName)).is(AncestryUtils.getEntityIdValue(source)));
        ProjectionOperation renamedRelativeIdField = Aggregation.project(AncestryUtils.DEFAULT_ID_FIELD_NAME).and(AncestryUtils.DEFAULT_ID_FIELD_NAME).as(CustomRelative.RELATIVE_ID);

        return checkIfRelativeIdExists(parentCollectionName, Aggregation.newAggregation(matchId, renamedRelativeIdField));
    }

    protected String getCollectionNameFromEntity (Class<?> clazz) {
        org.springframework.data.mongodb.core.mapping.Document docAnno = clazz.getAnnotation(org.springframework.data.mongodb.core.mapping.Document.class);
        String name;
        if (!docAnno.value().isEmpty()) {
            name = docAnno.value();
        } else if (!docAnno.collection().isEmpty()) {
            name = docAnno.collection();
        } else {
            char[] chars = clazz.getSimpleName().toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (i == 0) {
                    sb.append(Character.toString(chars[0]).toLowerCase());
                } else {
                    sb.append(chars[i]);
                }
            }
            name = sb.toString();
        }
        return name;
    }
}
