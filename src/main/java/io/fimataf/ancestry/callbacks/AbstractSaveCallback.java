package io.fimataf.ancestry.callbacks;

import io.fimataf.ancestry.utils.AncestryUtils;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author fima
 * Created on: 24/01/2021
 */
public abstract class AbstractSaveCallback implements ReflectionUtils.FieldCallback {

    protected MongoOperations mongoOperations;
    protected String collectionName;
    protected Document document;
    protected Object source;

    public AbstractSaveCallback(MongoOperations mongoOperations, String collectionName, Document document, Object source) {
        this.mongoOperations = mongoOperations;
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
            mongoOperations.getConverter().write(field.get(source), childDocument);
            document.put(docFieldName, childDocument);
        }
    }
}
