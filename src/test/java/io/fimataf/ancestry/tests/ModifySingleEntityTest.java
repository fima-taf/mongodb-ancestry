package io.fimataf.ancestry.tests;

import io.fimataf.ancestry.LocalMongodbTest;
import io.fimataf.ancestry.entities.base.single.*;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author fima
 * Created on: 24/01/2021
 */
public class ModifySingleEntityTest extends LocalMongodbTest {


    @Test
    void modifyEntityWithSimpleChildAnnotationAndChildIsNullOnChangeDelete () {
        ChildA child = new ChildA("modifyEntityWithChildAnnotationAndChildIsNullOnChangeDelete");
        ParentA parent = new ParentA("modifyEntityWithChildAnnotationAndChildIsNullOnChangeDelete", child);
        ParentA savedParent = mongoTemplate.insert(parent);

        savedParent.setName("modifyEntityWithChildAnnotationAndChildIsNullOnChangeDelete modified");
        savedParent.setChildA(null);
        ParentA modifiedParent = mongoTemplate.save(savedParent);
        Assertions.assertNotNull(modifiedParent);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentA expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentA.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNull(expParent.get_childAId());

        Query q2 = new Query();
        q2.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(child.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildA exparentA = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.single.ChildA.class);

        Assertions.assertNull(exparentA);
    }

    @Test
    void modifyEntityWithChildAnnotationAndChildIsNullOnChangeDetach () {
        ChildA child = new ChildA("modifyEntityWithChildAnnotationAndChildIsNullOnChangeDetach");
        ParentD parent = new ParentD("modifyEntityWithChildAnnotationAndChildIsNullOnChangeDetach", child);
        ParentD savedParent = mongoTemplate.insert(parent);

        savedParent.setName("modifyEntityWithChildAnnotationAndChildIsNullOnChangeDetach modified");
        savedParent.setChildA(null);
        ParentD modifiedParent = mongoTemplate.save(savedParent);
        Assertions.assertNotNull(modifiedParent);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentD expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentD.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNull(expParent.get_childAId());

        Query q2 = new Query();
        q2.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(child.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildA exparentA = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.single.ChildA.class);

        Assertions.assertNotNull(exparentA);
        Assertions.assertNull(exparentA.get_parentAId());
    }

    @Test
    void modifyEntityWithChildAndParentAnnotations () {
        ChildB child  = new ChildB("modifyEntityWithChildAndParentAnnotations");
        ParentB parent = new ParentB("modifyEntityWithChildAndParentAnnotations", child);
        ParentB savedParent = mongoTemplate.insert(parent);

        savedParent.setName("modifyEntityWithChildAndParentAnnotations modified");
        ParentB modifiedParent = mongoTemplate.save(savedParent);
        Assertions.assertNotNull(modifiedParent);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentB")).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildB exparentB = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ChildB.class);

        Assertions.assertNotNull(exparentB);
        Assertions.assertNotNull(exparentB.get_parentBId());
        Assertions.assertEquals(parent.getId(), exparentB.get_parentBId());
    }

    @Test
    void modifyEntityWithChildAndParentAnnotationsAndChildIsNullOnChangeDelete () {
        ChildB child  = new ChildB("Doctor");
        ParentB parent = new ParentB("modifyEntityWithChildAndParentAnnotationsAndChildIsNull", child);
        ParentB savedParent = mongoTemplate.insert(parent);

        savedParent.setName("C modified");
        savedParent.setChildB(null);
        ParentB modifiedParent = mongoTemplate.save(savedParent);
        Assertions.assertNotNull(modifiedParent);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentB")).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildB exparentB = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ChildB.class);

        Assertions.assertNull(exparentB);
    }

    @Test
    void modifyEntityWithChildAndParentAnnotationsAndChildIsNullOnChangeDetach () {
        ChildC child  = new ChildC("ChildC");
        ParentC parent = new ParentC("modifyEntityWithChildAndParentAnnotationsAndChildIsNull", child);
        ParentC savedParent = mongoTemplate.insert(parent);

        savedParent.setName("C modified");
        savedParent.setChildC(null);
        ParentC modifiedParent = mongoTemplate.save(savedParent);
        Assertions.assertNotNull(modifiedParent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(child.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildC exparentC = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ChildC.class);

        Assertions.assertNotNull(exparentC);
        Assertions.assertNull(exparentC.get_parentCId());
    }

    @Test
    void modifyEntityWithChildAnnotationAndReplaceChildOnChangeDelete () {
        ChildA child = new ChildA("first");
        ParentA parent = new ParentA("modifyEntityWithChildAnnotationAndReplaceChildOnChangeDelete", child);

        ParentA modifiedParent = mongoTemplate.insert(parent);
        Assertions.assertNotNull(modifiedParent);

        ChildA child2 = new ChildA("second");
        modifiedParent.setChildA(child2);
        ParentA modifiedParent2 = mongoTemplate.save(modifiedParent);
        Assertions.assertNotNull(modifiedParent2);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentA expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentA.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertEquals(expParent.get_childAId(), child2.getId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(child.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildA exparentA = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.single.ChildA.class);

        Assertions.assertNull(exparentA);
    }

    @Test
    void modifyParentEntityWithChildAnnotationAndReplaceChildOnChangeDetach () {
        ChildA child = new ChildA("modifyParentEntityWithChildAnnotationAndReplaceChildOnChangeDetach first");
        ParentD parent = new ParentD("modifyParentEntityWithChildAnnotationAndReplaceChildOnChangeDetach", child);

        ParentD modifiedParent = mongoTemplate.insert(parent);
        Assertions.assertNotNull(modifiedParent);

        ChildA child2 = new ChildA("modifyParentEntityWithChildAnnotationAndReplaceChildOnChangeDetach second");
        modifiedParent.setChildA(child2);
        ParentD modifiedParent2 = mongoTemplate.save(modifiedParent);
        Assertions.assertNotNull(modifiedParent2);

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentD expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentD.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertEquals(expParent.get_childAId(), child2.getId());

        Query q2 = new Query();
        q2.addCriteria(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(child.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildA exparentA = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.single.ChildA.class);

        Assertions.assertNotNull(exparentA);
        Assertions.assertNull(exparentA.get_parentAId());

    }

    @Test
    void modifyParentEntityWithChildAndParentAnnotationsAndReplaceChildOnChangeDelete () {
        ChildB child = new ChildB("modifyParentEntityWithChildAndParentAnnotationsAndReplaceChildOnChangeDelete first");
        ParentB parent = new ParentB("modifyParentEntityWithChildAndParentAnnotationsAndReplaceChildOnChangeDelete", child);

        ParentB modifiedParent = mongoTemplate.insert(parent);
        Assertions.assertNotNull(modifiedParent);

        ChildB child2 = new ChildB("modifyParentEntityWithChildAndParentAnnotationsAndReplaceChildOnChangeDelete second");
        modifiedParent.setChildB(child2);
        ParentB modifiedParent2 = mongoTemplate.save(modifiedParent);
        Assertions.assertNotNull(modifiedParent2);

        Query q = new Query(Criteria.where(AncestryUtils.generateAncestryIdFieldName("parentB")).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildB exparentB = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ChildB.class);

        Assertions.assertNotNull(exparentB);
        Assertions.assertEquals(exparentB.get_parentBId(), modifiedParent2.getId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(child.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildB exparentB2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.single.ChildB.class);

        Assertions.assertNull(exparentB2);

    }

    @Test
    void modifyChildEntityAndReplaceParentAsNull () {
        ChildG child = new ChildG("modifyChildEntityAndReplaceParent");
        ParentG parent1 = new ParentG("modifyChildEntityAndReplaceParent", child);
        ParentG savedParent1 = mongoTemplate.insert(parent1);

        Assertions.assertNotNull(savedParent1);

        child.setParentG(null);
        ChildG modifiedChild = mongoTemplate.save(child);

        Assertions.assertNotNull(modifiedChild);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent1.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentG expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentG.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNull(expParent.get_childGId());

    }

    @Test
    void modifyChildEntityAndReplaceParent () {
        ChildG child = new ChildG("modifyChildEntityAndReplaceParent");
        ParentG parent1 = new ParentG("modifyChildEntityAndReplaceParent", child);
        ParentG savedParent1 = mongoTemplate.insert(parent1);

        Assertions.assertNotNull(savedParent1);

        ParentG parent2 = new ParentG("modifyChildEntityAndReplaceParent second");
        ParentG savedParent2 = mongoTemplate.insert(parent2);
        child.setParentG(parent2);
        ChildG modifiedChild = mongoTemplate.save(child);

        Assertions.assertNotNull(modifiedChild);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent1.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentG expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ParentG.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNull(expParent.get_childGId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent2.getId()));
        io.fimataf.ancestry.entities.explicit.single.ParentG expParent2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.single.ParentG.class);

        Assertions.assertNotNull(expParent2);
        Assertions.assertNotNull(expParent2.get_childGId());
    }

    @Test
    void modifyChildEntityAndReplaceParentLinkToChild () {
        ChildF child = new ChildF("modifyChildEntityAndReplaceParentLinkToChild");
        ParentF parent1 = new ParentF("modifyChildEntityAndReplaceParentLinkToChild", child);
        ParentF savedParent1 = mongoTemplate.insert(parent1);

        Assertions.assertNotNull(savedParent1);

        ParentF parent2 = new ParentF("modifyChildEntityAndReplaceParentLinkToChild second");
        ParentF savedParent2 = mongoTemplate.insert(parent2);
        child.setParentF(parent2);
        ChildF modifiedChild = mongoTemplate.save(child);

        Assertions.assertNotNull(modifiedChild);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedChild.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildF expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ChildF.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertEquals(expParent.get_parentFId(), savedParent2.getId());

    }

    @Test
    void modifyChildEntityAndReplaceParentAsNullLinkToChild () {
        ChildF child = new ChildF("modifyChildEntityAndReplaceParentLinkToChild");
        ParentF parent1 = new ParentF("modifyChildEntityAndReplaceParentLinkToChild", child);
        ParentF savedParent1 = mongoTemplate.insert(parent1);

        Assertions.assertNotNull(savedParent1);

        child.setParentF(null);
        ChildF modifiedChild = mongoTemplate.save(child);

        Assertions.assertNotNull(modifiedChild);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedChild.getId()));
        io.fimataf.ancestry.entities.explicit.single.ChildF expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.single.ChildF.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNull(expParent.get_parentFId());

    }

}
