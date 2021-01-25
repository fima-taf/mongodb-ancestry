package io.fimataf.ancestry.tests;

import io.fimataf.ancestry.LocalMongodbTest;
import io.fimataf.ancestry.entities.base.ParentA;
import io.fimataf.ancestry.entities.base.ChildA;
import io.fimataf.ancestry.entities.base.ParentB;
import io.fimataf.ancestry.entities.base.ChildB;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author fima
 * Created on: 24/01/2021
 */
public class EditSingleEntityTest extends LocalMongodbTest {


    @Test
    void editEntityWithChildAnnotation () {
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

    @Test
    void editEntityWithChildAnnotationAndChildIsNull () {
        ChildA cA = new ChildA("Bar");
        ParentA pA = new ParentA("editEntityWithChildAnnotationAndChildIsNull", cA);
        ParentA savedPA = mongoTemplate.insert(pA);

        savedPA.setName("Bar edited");
        savedPA.setChildA(null);
        ParentA editedPA = mongoTemplate.save(savedPA);
        Assertions.assertNotNull(editedPA);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedPA.getId()));
        io.fimataf.ancestry.entities.explicit.ParentA expPA = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.ParentA.class);

        Assertions.assertNotNull(expPA);
        Assertions.assertNotNull(expPA.get_childAId());
        Assertions.assertEquals(cA.getId(), expPA.get_childAId());
    }

    @Test
    void editEntityWithChildAndParentAnnotations () {
        ChildB cB  = new ChildB("Doctor");
        ParentB pB = new ParentB("editEntityWithChildAndParentAnnotations", cB);
        ParentB savedPB = mongoTemplate.insert(pB);

        savedPB.setName("C edited");
        ParentB editedPB = mongoTemplate.save(savedPB);
        Assertions.assertNotNull(editedPB);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentB")).is(editedPB.getId()));
        io.fimataf.ancestry.entities.explicit.ChildB expCB = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.ChildB.class);

        Assertions.assertNotNull(expCB);
        Assertions.assertNotNull(expCB.get_parentBId());
        Assertions.assertEquals(pB.getId(), expCB.get_parentBId());
    }

    @Test
    void editEntityWithChildAndParentAnnotationsAndChildIsNull () {
        ChildB cB  = new ChildB("Doctor");
        ParentB pB = new ParentB("editEntityWithChildAndParentAnnotationsAndChildIsNull", cB);
        ParentB savedPB = mongoTemplate.insert(pB);

        savedPB.setName("C edited");
        savedPB.setChildB(null);
        ParentB editedPB = mongoTemplate.save(savedPB);
        Assertions.assertNotNull(editedPB);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentB")).is(editedPB.getId()));
        io.fimataf.ancestry.entities.explicit.ChildB expCB = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.ChildB.class);

        Assertions.assertNotNull(expCB);
        Assertions.assertNotNull(expCB.get_parentBId());
        Assertions.assertEquals(pB.getId(), expCB.get_parentBId());
    }
}
