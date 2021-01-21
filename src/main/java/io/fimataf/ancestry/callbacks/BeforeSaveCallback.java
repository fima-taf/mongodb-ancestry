package io.fimataf.ancestry.callbacks;

import com.kanbordz.interceptors.annotations.Child;
import com.kanbordz.interceptors.annotations.Parent;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import java.lang.reflect.Field;

/**
 * @author fima
 * Created on: 20/01/2021
 */
public class BeforeSaveCallback implements FieldCallback {

    private MongoOperations mongoOperations;
    private String collectionName;
    private Document document;

    public BeforeSaveCallback (MongoOperations mongoOperations, String collectionName, Document document) {
        this.mongoOperations = mongoOperations;
        this.collectionName = collectionName;
        this.document = document;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException {
        ReflectionUtils.makeAccessible(field);
        if (field.isAnnotationPresent(Child.class)) {
            childModifications();
        } else if (field.isAnnotationPresent(Parent.class)) {
            parentModifications();
        }
    }

    private void childModifications () {
        //delete the child from the document

    }

    private void parentModifications () {
        // delete the parent from the document and add the parentId

    }

}
