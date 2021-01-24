package io.fimataf.ancestry;

import io.fimataf.ancestry.callbacks.AfterSaveCallback;
import io.fimataf.ancestry.callbacks.BeforeSaveCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

/**
 * @author fima
 * Created on: 20/01/2021
 */
public class MongodbAncestryEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        if (ReflectionUtils.isFieldsWithAnnotationExists(event.getSource(), Id.class)) {
            ReflectionUtils.doWithFields(event.getSource().getClass(), new BeforeSaveCallback(mongoTemplate, event.getCollectionName(), event.getDocument(), event.getSource()));
        }
        super.onBeforeSave(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        if (ReflectionUtils.isFieldsWithAnnotationExists(event.getSource(), Id.class)) {
            ReflectionUtils.doWithFields(event.getSource().getClass(), new AfterSaveCallback(mongoTemplate, event.getCollectionName(), event.getDocument(), event.getSource()));
        }
        super.onAfterSave(event);
    }


}
