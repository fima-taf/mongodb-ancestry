package io.fimataf.ancestry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

/**
 * @author fima
 * Created on: 20/01/2021
 */
public class FamilyMongoEventListener extends AbstractMongoEventListener<Object> {

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {
        if (ReflectionUtils.isFieldsWithAnnotationExists(event.getSource(), Id.class)) {
//            event.getDocument()
//            ReflectionUtils.doWithFields(event.getSource().getClass(), );
        }
        super.onBeforeSave(event);
    }

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        super.onAfterSave(event);
    }


}
