package io.fimataf.ancestry;

import io.fimataf.ancestry.callbacks.AfterSaveCallback;
import io.fimataf.ancestry.callbacks.BeforeSaveCallback;
import io.fimataf.ancestry.repositories.DBActions;
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
    private DBActions dbActions;

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        if (ReflectionUtils.isFieldsWithAnnotationExists(event.getSource(), Id.class)) {
            ReflectionUtils.doWithFields(event.getSource().getClass(), new BeforeSaveCallback(dbActions, event.getCollectionName(), event.getDocument(), event.getSource()));
        }
        super.onBeforeSave(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        if (ReflectionUtils.isFieldsWithAnnotationExists(event.getSource(), Id.class)) {
            ReflectionUtils.doWithFields(event.getSource().getClass(), new AfterSaveCallback(dbActions, event.getCollectionName(), event.getDocument(), event.getSource()));
        }
        super.onAfterSave(event);
    }


}
