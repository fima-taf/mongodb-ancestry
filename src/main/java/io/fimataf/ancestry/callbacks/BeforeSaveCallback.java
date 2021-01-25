package io.fimataf.ancestry.callbacks;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.annotations.Parent;
import io.fimataf.ancestry.entities.CustomChild;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.bson.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author fima
 * Created on: 20/01/2021
 */
public class BeforeSaveCallback extends AbstractSaveCallback {

    private String childFieldName;

    public BeforeSaveCallback (MongoOperations mongoOperations, String collectionName, Document document, Object source) {
        super(mongoOperations, collectionName, document, source);
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(Child.class)) {
            childFieldName = AncestryUtils.getDocumentFieldName(field);
            removeChildFromDocument();
            Object child = field.get(source);
            if (!field.getAnnotation(Child.class).linkToChild()) {
                saveChild(child, field);
            }
        } else if (field.isAnnotationPresent(Parent.class)) {
            saveChildWithParentId(field);
        }
    }

    private void removeChildFromDocument() {
        document.remove(childFieldName);
    }

    private void saveChild (Object child, Field parentField) throws IllegalAccessException {
        if (child != null) {
            Object savedChild = mongoOperations.save(child);
            for (Field field : savedChild.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    ReflectionUtils.makeAccessible(field);
                    Object childId = field.get(savedChild);
                    document.put(AncestryUtils.generateAncestryIdFieldName(childFieldName), childId.toString());
                    return;
                }
            }
        } else {
            isChildIdExists(parentField);
        }
    }

    private void saveChildWithParentId (Field parentField) throws IllegalAccessException {
        Object parent = parentField.get(source);
        if (parent == null) return;
        for (Field field : parent.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                ReflectionUtils.makeAccessible(field);
                Object parentId = field.get(parent);
                String parentDocName = AncestryUtils.getDocumentFieldName(parentField);
                document.remove(parentDocName);
                document.put(AncestryUtils.generateAncestryIdFieldName(parentDocName), parentId);
                return;
            }
        }
    }

    private void isChildIdExists (Field field) throws IllegalAccessException {
        MatchOperation matchId = Aggregation.match(new Criteria(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(AncestryUtils.getEntityIdValue(source)));
        ProjectionOperation excludeId = Aggregation.project(AncestryUtils.generateAncestryIdFieldName(childFieldName)).andExclude(AncestryUtils.DEFAULT_ID_FIELD_NAME);
        ProjectionOperation renamedChildId = Aggregation.project().and(AncestryUtils.generateAncestryIdFieldName(childFieldName)).as(CustomChild.CHILD_ID);

        Aggregation aggregation = Aggregation.newAggregation(matchId, excludeId, renamedChildId);

        AggregationResults<CustomChild> result = mongoOperations.aggregate(aggregation, collectionName, CustomChild.class);

        if (result.getMappedResults().size() == 1) {
            addChildToDocument(field, result.getMappedResults().get(0).getChildId());
        } else if (result.getMappedResults().size() > 1) {
            // for many to many support
        }
    }

}
