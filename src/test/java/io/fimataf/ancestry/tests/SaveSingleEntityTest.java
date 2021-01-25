package io.fimataf.ancestry.tests;

import io.fimataf.ancestry.LocalMongodbTest;
import io.fimataf.ancestry.entities.base.*;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author fima
 * Created on: 22/01/2021
 */
public class SaveSingleEntityTest extends LocalMongodbTest {

    @Test
    void saveEntityWithChildAnnotation () {
        ChildA cA = new ChildA("Bar Save");
        ParentA pA = new ParentA("saveEntityWithChildAnnotation", cA);
        ParentA savedPA = mongoTemplate.insert(pA);

        Assertions.assertNotNull(savedPA.getId());

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("childA")).is(savedPA.getChildA().getId()));
        io.fimataf.ancestry.entities.explicit.ParentA expPA = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.ParentA.class);

        Assertions.assertNotNull(expPA);
        Assertions.assertNotNull(expPA.get_childAId());
        Assertions.assertEquals(savedPA.getChildA().getId(), expPA.get_childAId());
    }

    @Test
    void saveEntityWithChildAnnotationAndChildIsNull () {
        ParentA pA = new ParentA("saveEntityWithChildAnnotationAndChildIsNull");
        ParentA savedPA = mongoTemplate.insert(pA);

        Assertions.assertNotNull(savedPA.getId());

    }

    @Test
    void saveChildEntityWithParentAnnotationAndParentIsNull () {
        ChildB cB = new ChildB("saveChildEntityWithParentAnnotationAndParentIsNull");
        ChildB savedCB = mongoTemplate.insert(cB);

        Assertions.assertNotNull(savedCB.getId());

    }

    @Test
    void saveEntityWithChildAndParentAnnotations () {
        ChildB cB  = new ChildB("Doctor");
        ParentB pB = new ParentB("saveEntityWithChildAndParentAnnotations", cB);
        ParentB savedPB = mongoTemplate.insert(pB);

        Assertions.assertNotNull(savedPB.getId());

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentB")).is(savedPB.getId()));
        io.fimataf.ancestry.entities.explicit.ChildB expCB = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.ChildB.class);

        Assertions.assertNotNull(expCB);
        Assertions.assertNotNull(expCB.get_parentBId());
        Assertions.assertEquals(savedPB.getId(), expCB.get_parentBId());

        Assertions.assertNull(savedPB.getChildB().getParentB());
    }

    @Test
    void saveEntityWithChildAndParentAnnotationsAndKeepParent () {
        ChildC cC  = new ChildC("Foo");
        ParentC pC = new ParentC("saveEntityWithChildAndParentAnnotationsAndKeepParent", cC);
        ParentC savedPC = mongoTemplate.insert(pC);

        Assertions.assertNotNull(savedPC.getId());

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentC")).is(savedPC.getId()));
        io.fimataf.ancestry.entities.explicit.ChildC expCC = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.ChildC.class);

        Assertions.assertNotNull(expCC);
        Assertions.assertNotNull(expCC.get_parentCId());
        Assertions.assertEquals(savedPC.getId(), expCC.get_parentCId());

        Assertions.assertNotNull(savedPC.getChildC().getParentC());
    }

}
