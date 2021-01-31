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
 * Created on: 30/01/2021
 */
public class SaveMultipleEntitiesTest extends LocalMongodbTest {

    @Test
    void saveParentWithTwoSimpleChildren () {
        ParentA parent = new ParentA("saveParentWithTwoChildren");
        ChildA firstChild = new ChildA("saveParentWithTwoChildren first");
        ChildA secondChild = new ChildA("saveParentWithTwoChildren second");

        parent.setChildAFirst(firstChild);
        parent.setChildASecond(secondChild);

        ParentA savedParent = mongoTemplate.insert(parent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ParentA expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ParentA.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNotNull(expParent.get_childAFirstId());
        Assertions.assertEquals(expParent.get_childAFirstId(), firstChild.getId());
        Assertions.assertNotNull(expParent.get_childASecondId());
        Assertions.assertEquals(expParent.get_childASecondId(), secondChild.getId());
    }

    @Test
    void saveParentWithTwoDifferentChildren () {
        ParentB parent = new ParentB("saveParentWithTwoDifferentChildren");
        ChildB1 childB1 = new ChildB1("saveParentWithTwoDifferentChildren");
        ChildB2 childB2 = new ChildB2("saveParentWithTwoDifferentChildren");
        parent.setChildB1(childB1);
        parent.setChildB2(childB2);

        ParentB savedParent = mongoTemplate.insert(parent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ParentB expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ParentB.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertNotNull(expParent.get_childB1Id());
        Assertions.assertEquals(expParent.get_childB1Id(), childB1.getId());
        Assertions.assertNotNull(expParent.get_childB2Id());
        Assertions.assertEquals(expParent.get_childB2Id(), childB2.getId());

    }

    @Test
    void saveParentWithTwoChildrenAndLinkOnChild () {
        ParentC parent = new ParentC("saveParentWithTwoChildrenAndLinkOnChild");
        ChildC1 childC1 = new ChildC1("saveParentWithTwoChildrenAndLinkOnChild");
        ChildC2 childC2 = new ChildC2("saveParentWithTwoChildrenAndLinkOnChild");
        parent.setChildC1(childC1);
        parent.setChildC2(childC2);

        ParentC savedParent = mongoTemplate.insert(parent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childC1.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildC1 expChild1 = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ChildC1.class);

        Assertions.assertNotNull(expChild1);
        Assertions.assertEquals(expChild1.get_parentCId(), savedParent.getId());


        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childC2.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildC2 expChild2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.multiple.ChildC2.class);

        Assertions.assertNotNull(expChild2);
        Assertions.assertEquals(expChild2.get_parentCId(), savedParent.getId());
    }

    @Test
    void saveParentWithTwoChildrenAndOneLinkOnChild () {
        ParentD parent = new ParentD("saveParentWithTwoChildrenAndOneLinkOnChild");
        ChildD1 childD1 = new ChildD1("saveParentWithTwoChildrenAndOneLinkOnChild");
        ChildD2 childD2 = new ChildD2("saveParentWithTwoChildrenAndOneLinkOnChild");
        parent.setChildD1(childD1);
        parent.setChildD2(childD2);

        ParentD savedParent = mongoTemplate.insert(parent);

        Query q = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(savedParent.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ParentD expParent = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.multiple.ParentD.class);

        Query q2 = new Query(Criteria.where(AncestryUtils.DEFAULT_ID_FIELD_NAME).is(childD2.getId()));
        io.fimataf.ancestry.entities.explicit.multiple.ChildD2 expChild2 = mongoTemplate.findOne(q2, io.fimataf.ancestry.entities.explicit.multiple.ChildD2.class);

        Assertions.assertNotNull(expParent);
        Assertions.assertEquals(expParent.get_childD1Id(), childD1.getId());

        Assertions.assertNotNull(expChild2);
        Assertions.assertEquals(expChild2.get_parentDId(), savedParent.getId());
    }
}
