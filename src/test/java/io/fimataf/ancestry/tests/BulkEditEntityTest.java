package io.fimataf.ancestry.tests;

import io.fimataf.ancestry.LocalMongodbTest;
import io.fimataf.ancestry.entities.base.ChildA;
import io.fimataf.ancestry.entities.base.ParentA;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author fima
 * Created on: 26/01/2021
 */
public class BulkEditEntityTest extends LocalMongodbTest {

    @Test
    void modifyEntityWithChildAnnotation () {
        ChildA cA = new ChildA("Bar");
        ParentA pA = new ParentA("editEntityWithChildAnnotation", cA);
        ParentA savedPA = mongoTemplate.insert(pA);

        savedPA.setName("Foo edited");
        savedPA.getChildA().setName("edited email");
        ParentA editedPA = mongoTemplate.save(savedPA);
        Assertions.assertNotNull(editedPA);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedPA.getId()));
        io.fimataf.ancestry.entities.explicit.ParentA expPA = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.ParentA.class);

        Assertions.assertNotNull(expPA);
        Assertions.assertNotNull(expPA.get_childAId());
    }
}
