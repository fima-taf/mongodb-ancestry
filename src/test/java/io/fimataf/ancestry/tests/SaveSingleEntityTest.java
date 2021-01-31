package io.fimataf.ancestry.tests;

import io.fimataf.ancestry.LocalMongodbTest;
import io.fimataf.ancestry.entities.base.single.*;
import io.fimataf.ancestry.exceptions.NoParentFoundException;
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
    void saveParentEntityWithSimpleChild () {
        ChildA child = new ChildA("Bar Save");
        ParentA parent = new ParentA("saveEntityWithChildAnnotation", child);
        ParentA savedParent = mongoTemplate.insert(parent);

        Assertions.assertNotNull(savedParent.getId());

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("childA")).is(savedParent.getChildA().getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentA expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentA.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNotNull(expParent.get_childAId());
        Assertions.assertEquals(savedParent.getChildA().getId(), expParent.get_childAId());
    }

    @Test
    void saveParentEntityAndChildIsNull () {
        ParentA parent = new ParentA("saveEntityWithChildAnnotationAndChildIsNull");
        ParentA savedParent = mongoTemplate.insert(parent);

        Assertions.assertNotNull(savedParent.getId());

    }

    @Test
    void saveChildEntityWithParentAnnotationAndParentIsNull () {
        ChildB child = new ChildB("saveChildEntityWithParentAnnotationAndParentIsNull");
        ChildB savedChild = mongoTemplate.insert(child);

        Assertions.assertNotNull(savedChild.getId());

    }

    @Test
    void saveChildEntityWithParentAnnotationAndParentIsNotSaved () {
        ChildB child = new ChildB("saveChildEntityWithParentAnnotationAndParentIsNull");
        ParentB parent = new ParentB();
        parent.setName("saveChildEntityWithParentAnnotationAndParentIsNull");
        child.setParentB(parent);
        try {
            mongoTemplate.insert(child);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof NoParentFoundException);
            Assertions.assertNull(child.getId());
        }
    }

    @Test
    void saveEntityWithChildAndParentAnnotations () {
        ChildB child  = new ChildB("ChildB");
        ParentB parent = new ParentB("saveEntityWithChildAndParentAnnotations", child);
        ParentB savedParent = mongoTemplate.insert(parent);

        Assertions.assertNotNull(savedParent.getId());

        Query q = new Query(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentB")).is(savedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildB expChild = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ChildB.class);

        Assertions.assertNotNull(expChild);
        Assertions.assertNotNull(expChild.get_parentBId());
        Assertions.assertEquals(savedParent.getId(), expChild.get_parentBId());

        Assertions.assertNull(savedParent.getChildB().getParentB().getChildB());
    }


    @Test
    void saveParentWithChildThatHasOtherParent () {
        ChildG child = new ChildG("saveParentWithChildThatHasOtherParent");
        ParentG parent1 = new ParentG("saveParentWithChildThatHasOtherParent", child);
        ParentG savedParent1 = mongoTemplate.save(parent1);

        ParentG parent2 = new ParentG("saveParentWithChildThatHasOtherParent", child);
        ParentG savedParent2 = mongoTemplate.save(parent2);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent1.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentG expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentG.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNull(expParent.get_childGId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent2.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentG expParent2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.single.ParentG.class);

        Assertions.assertNotNull(expParent2);
        Assertions.assertNotNull(expParent2.getId());
        Assertions.assertEquals(expParent2.get_childGId(), child.getId());
    }

}
