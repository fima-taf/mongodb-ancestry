package io.fimataf.ancestry.tests;

import io.fimataf.ancestry.LocalMongodbTest;
import io.fimataf.ancestry.entities.base.multiple.*;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author fima
 * Created on: 31/01/2021
 */
public class ModifyMultipleEntitiesTest extends LocalMongodbTest {

    @Test
    void modifyParentWithTwoDifferentChildrenOnChangeDelete () {
        ParentB parent = new ParentB("modifyParentWithTwoDifferentChildrenOnChangeDelete");
        ChildB1 childB1 = new ChildB1("modifyParentWithTwoDifferentChildrenOnChangeDelete");
        ChildB2 childB2 = new ChildB2("modifyParentWithTwoDifferentChildrenOnChangeDelete");
        parent.setChildB1(childB1);
        parent.setChildB2(childB2);

        ParentB savedParent = mongoTemplate.insert(parent);

        ChildB1 childB1Second = new ChildB1("modifyParentWithTwoDifferentChildrenOnChangeDelete second");
        ChildB2 childB2Second = new ChildB2("modifyParentWithTwoDifferentChildrenOnChangeDelete second");

        ChildB1 secondChildB1Saved = mongoTemplate.insert(childB1Second);
        ChildB2 secondChildB2Saved = mongoTemplate.insert(childB2Second);

        savedParent.setChildB1(secondChildB1Saved);
        savedParent.setChildB2(secondChildB2Saved);

        ParentB modifiedParent = mongoTemplate.save(savedParent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ParentB expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ParentB.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNotNull(expParent.get_childB1Id());
        Assertions.assertEquals(expParent.get_childB1Id(), secondChildB1Saved.getId());
        Assertions.assertNotNull(expParent.get_childB2Id());
        Assertions.assertEquals(expParent.get_childB2Id(), secondChildB2Saved.getId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childB1.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildB1 expChildB1 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.multiple.ChildB1.class);

        Assertions.assertNull(expChildB1);

        Query q3 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childB1.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildB2 expChildB2 = mongoTemplate.findOne(q3, io.fimataf.ancestry.entities.explicit.multiple.ChildB2.class);

        Assertions.assertNull(expChildB2);

    }

    @Test
    void modifyParentWithTwoDifferentChildrenOnChangeDetach () {
        ParentE parent = new ParentE("modifyParentWithTwoDifferentChildrenOnChangeDetach");
        ChildE1 childE1 = new ChildE1("modifyParentWithTwoDifferentChildrenOnChangeDetach");
        ChildE2 childE2 = new ChildE2("modifyParentWithTwoDifferentChildrenOnChangeDetach");
        parent.setChildE1(childE1);
        parent.setChildE2(childE2);

        ParentE savedParent = mongoTemplate.insert(parent);

        ChildE1 childE1Second = new ChildE1("modifyParentWithTwoDifferentChildrenOnChangeDetach second");
        ChildE2 childE2Second = new ChildE2("modifyParentWithTwoDifferentChildrenOnChangeDetach second");

        ChildE1 secondChildE1Saved = mongoTemplate.insert(childE1Second);
        ChildE2 secondChildE2Saved = mongoTemplate.insert(childE2Second);

        savedParent.setChildE1(secondChildE1Saved);
        savedParent.setChildE2(secondChildE2Saved);

        ParentE modifiedParent = mongoTemplate.save(savedParent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ParentE expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ParentE.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNotNull(expParent.get_childE1Id());
        Assertions.assertEquals(expParent.get_childE1Id(), secondChildE1Saved.getId());
        Assertions.assertNotNull(expParent.get_childE2Id());
        Assertions.assertEquals(expParent.get_childE2Id(), secondChildE2Saved.getId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childE1.getId()));
        ChildE1 expChildE1 = mongoTemplate.findOne(q2, ChildE1.class);

        Assertions.assertNotNull(expChildE1);

        Query q3 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childE2.getId()));
        ChildE2 expChildE2 = mongoTemplate.findOne(q3, ChildE2.class);

        Assertions.assertNotNull(expChildE2);

    }

    @Test
    void modifyParentWithTwoChildrenAndLinkOnChildOnChangeDelete () {
        ParentC parent = new ParentC("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDelete");
        ChildC1 childC1 = new ChildC1("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDelete");
        ChildC2 childC2 = new ChildC2("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDelete");
        parent.setChildC1(childC1);
        parent.setChildC2(childC2);

        ParentC savedParent = mongoTemplate.insert(parent);

        ChildC1 childC1Second = new ChildC1("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDelete second");
        ChildC2 childC2Second = new ChildC2("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDelete second");

        ChildC1 savedChildC1Second = mongoTemplate.insert(childC1Second);
        ChildC2 savedChildC2Second = mongoTemplate.insert(childC2Second);

        savedParent.setChildC1(savedChildC1Second);
        savedParent.setChildC2(savedChildC2Second);

        ParentC modifiedParent = mongoTemplate.save(savedParent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childC1.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildC1 expChildC1 = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ChildC1.class);

        Assertions.assertNull(expChildC1);

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childC2.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildC2 expChildC2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.multiple.ChildC2.class);

        Assertions.assertNull(expChildC2);

        Query q3 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childC1Second.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildC1 expChildC1Second = mongoTemplate.findOne(q3, io.fimataf.ancestry.entities.explicit.multiple.ChildC1.class);

        Assertions.assertNotNull(expChildC1Second);
        Assertions.assertEquals(expChildC1Second.get_parentCId(), modifiedParent.getId());

        Query q4 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childC2Second.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildC2 expChildC2Second = mongoTemplate.findOne(q4, io.fimataf.ancestry.entities.explicit.multiple.ChildC2.class);

        Assertions.assertNotNull(expChildC2Second);
        Assertions.assertEquals(expChildC2Second.get_parentCId(), modifiedParent.getId());
    }

    @Test
    void modifyParentWithTwoChildrenAndLinkOnChildOnChangeDetach () {
        ParentF parent = new ParentF("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDetach");
        ChildF1 childF1 = new ChildF1("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDetach");
        ChildF2 childF2 = new ChildF2("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDetach");
        parent.setChildF1(childF1);
        parent.setChildF2(childF2);

        ParentF savedParent = mongoTemplate.insert(parent);

        ChildF1 childF1Second = new ChildF1("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDetach second");
        ChildF2 childF2Second = new ChildF2("modifyParentWithTwoChildrenAndLinkOnChildOnChangeDetach second");

        ChildF1 savedChildF1Second = mongoTemplate.insert(childF1Second);
        ChildF2 savedChildF2Second = mongoTemplate.insert(childF2Second);

        savedParent.setChildF1(savedChildF1Second);
        savedParent.setChildF2(savedChildF2Second);

        ParentF modifiedParent = mongoTemplate.save(savedParent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childF1.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildF1 expChildF1 = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ChildF1.class);

        Assertions.assertNotNull(expChildF1);
        Assertions.assertNull(expChildF1.get_parentFId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childF2.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildF2 expChildF2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.multiple.ChildF2.class);

        Assertions.assertNotNull(expChildF2);
        Assertions.assertNull(expChildF2.get_parentFId());

        Query q3 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childF1Second.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildF1 expChildF1Second = mongoTemplate.findOne(q3, io.fimataf.ancestry.entities.explicit.multiple.ChildF1.class);

        Assertions.assertNotNull(expChildF1Second);
        Assertions.assertNotNull(expChildF1Second.get_parentFId());
        Assertions.assertEquals(expChildF1Second.get_parentFId(), modifiedParent.getId());

        Query q4 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childF2Second.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildF2 expChildF2Second = mongoTemplate.findOne(q4, io.fimataf.ancestry.entities.explicit.multiple.ChildF2.class);

        Assertions.assertNotNull(expChildF2Second);
        Assertions.assertNotNull(expChildF2Second.get_parentFId());
        Assertions.assertEquals(expChildF2Second.get_parentFId(), modifiedParent.getId());
    }

    @Test
    void modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDelete () {
        ParentD parent = new ParentD("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDelete");
        ChildD1 childD1 = new ChildD1("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDelete");
        ChildD2 childD2 = new ChildD2("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDelete");
        parent.setChildD1(childD1);
        parent.setChildD2(childD2);

        ParentD savedParent = mongoTemplate.insert(parent);

        ChildD1 childD1Second = new ChildD1("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDelete second");
        ChildD2 childD2Second = new ChildD2("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDelete second");

        ChildD1 savedChildD1Second = mongoTemplate.insert(childD1Second);
        ChildD2 savedChildD2Second = mongoTemplate.insert(childD2Second);

        savedParent.setChildD1(savedChildD1Second);
        savedParent.setChildD2(savedChildD2Second);

        ParentD modifiedParent = mongoTemplate.save(savedParent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ParentD expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ParentD.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertEquals(expParent.get_childD1Id(), savedChildD1Second.getId());

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childD1.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildD1 expChildD1 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.multiple.ChildD1.class);

        Assertions.assertNull(expChildD1);

        Query q3 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childD2.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildD2 expChildD2 = mongoTemplate.findOne(q3, io.fimataf.ancestry.entities.explicit.multiple.ChildD2.class);

        Assertions.assertNull(expChildD2);

        Query q4 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childD2Second.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildD2 expChildD2Second = mongoTemplate.findOne(q4, io.fimataf.ancestry.entities.explicit.multiple.ChildD2.class);

        Assertions.assertNotNull(expChildD2Second);
        Assertions.assertEquals(expChildD2Second.get_parentDId(), modifiedParent.getId());
    }

    @Test
    void modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDetach () {
        ParentG parent = new ParentG("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDetach");
        ChildG1 childG1 = new ChildG1("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDetach");
        ChildG2 childG2 = new ChildG2("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDetach");
        parent.setChildG1(childG1);
        parent.setChildG2(childG2);

        ParentG savedParent = mongoTemplate.insert(parent);

        ChildG1 childG1Second = new ChildG1("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDetach second");
        ChildG2 childG2Second = new ChildG2("modifyParentWithTwoChildrenAndOneLinkOnChildOnChangeDetach second");

        ChildG1 savedChildG1Second = mongoTemplate.insert(childG1Second);
        ChildG2 savedChildG2Second = mongoTemplate.insert(childG2Second);

        savedParent.setChildG1(savedChildG1Second);
        savedParent.setChildG2(savedChildG2Second);

        ParentG modifiedParent = mongoTemplate.save(savedParent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(modifiedParent.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ParentG expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ParentG.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertEquals(expParent.get_childG1Id(), savedChildG1Second.getId());

        Query q1 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childG1.getId()));
        ChildG1 expChildG1 = mongoTemplate.findOne(q1, ChildG1.class);

        Assertions.assertNotNull(expChildG1);

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childG2.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildG2 expChildG2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.multiple.ChildG2.class);

        Assertions.assertNotNull(expChildG2);
        Assertions.assertNull(expChildG2.get_parentGId());

        Query q3 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedChildG2Second.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildG2 expChildG2Second = mongoTemplate.findOne(q3, io.fimataf.ancestry.entities.explicit.multiple.ChildG2.class);

        Assertions.assertNotNull(expChildG2Second);
        Assertions.assertEquals(expChildG2Second.get_parentGId(), modifiedParent.getId());

    }
}
