package io.fimataf.ancestry.repositories;

import io.fimataf.ancestry.entities.CustomRelative;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationUpdate;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import java.util.List;

/**
 * @author fima
 * Created on: 28/01/2021
 */
public class DBActions {

    @Autowired
    private MongoOperations mongoOperations;


    public void convertObjectIntoDocument (Object source, Document document) {
        mongoOperations.getConverter().write(source, document);
    }

    public List<?> findWithQueryAndClass (Query q, Class<?> clazz) {
        return mongoOperations.find(q, clazz);
    }

    public <T> List<T> findWithAggregation (Aggregation aggregation, String collectionName, Class<T> clazz) {
        return mongoOperations.aggregate(aggregation, collectionName, clazz).getMappedResults();
    }

    public void removeEntity (Object object) {
        mongoOperations.remove(object);
    }

    public void removeByQueryAndClass (Query q, Class<?> clazz) {
        mongoOperations.remove(q, clazz);
    }

    public Object saveEntity (Object object) {
        return mongoOperations.save(object);
    }

    public void updateFields (String fieldName, List<String> ids, Class<?> clazz) {
//        AggregationUpdate.
        Query q = new Query(new Criteria(AncestryUtils.DEFAULT_ID_FIELD_NAME).in(ids.toArray()));
        Update u = new Update();
        u.set(fieldName, null);
        mongoOperations.updateMulti(q, u, clazz);
    }
}
