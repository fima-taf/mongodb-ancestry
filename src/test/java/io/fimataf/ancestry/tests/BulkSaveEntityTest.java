package io.fimataf.ancestry.tests;

import io.fimataf.ancestry.LocalMongodbTest;
import io.fimataf.ancestry.entities.base.ChildA;
import io.fimataf.ancestry.entities.base.ChildB;
import io.fimataf.ancestry.entities.base.ParentA;
import io.fimataf.ancestry.utils.AncestryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fima
 * Created on: 26/01/2021
 */
public class BulkSaveEntityTest extends LocalMongodbTest {

    @Test
    void saveEntityWithChildAnnotation () {
        ChildA cA1 = new ChildA("first");
        ChildA cA2 = new ChildA("second");
        ParentA pA1 = new ParentA("saveEntityWithChildAnnotation", cA1);
        ParentA pA2 = new ParentA("saveEntityWithChildAnnotation", cA2);
        List<ParentA> parents = Arrays.asList(pA1, pA2);
        List<ParentA> savedParents = (List<ParentA>) mongoTemplate.insertAll(parents);

        Assertions.assertNotNull(savedParents);
        Assertions.assertEquals(parents.size(), savedParents.size());
        List<String> childrenIds = Stream.of(cA1, cA2).map(ChildA::getId).collect(Collectors.toList());
        savedParents.forEach(p -> Assertions.assertTrue(childrenIds.contains(p.getChildA().getId())));

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("childA")).in(cA1.getId(), cA2.getId()));
        List<io.fimataf.ancestry.entities.explicit.ParentA> expPAList = mongoTemplate.find(q, io.fimataf.ancestry.entities.explicit.ParentA.class);

        Assertions.assertNotNull(expPAList);
        Assertions.assertEquals(expPAList.size(), savedParents.size());

        expPAList.forEach(p -> Assertions.assertTrue(childrenIds.contains(p.get_childAId())));
    }

    @Test
    void saveEntityWithChildAnnotationAndChildIsNull () {
        ParentA pA1 = new ParentA("saveEntityWithChildAnnotationAndChildIsNull");
        ParentA pA2 = new ParentA("saveEntityWithChildAnnotationAndChildIsNull");
        ParentA pA3 = new ParentA("saveEntityWithChildAnnotationAndChildIsNull");
        List<ParentA> pAList = Arrays.asList(pA1, pA2, pA3);

        List<ParentA> savedPAList = (List<ParentA>) mongoTemplate.insertAll(pAList);

        Assertions.assertEquals(pAList.size(), savedPAList.size());
        savedPAList.forEach(p -> Assertions.assertNotNull(p.getId()));

    }

    @Test
    void saveChildEntityWithParentAnnotationAndParentIsNull () {
        ChildB cB1 = new ChildB("saveChildEntityWithParentAnnotationAndParentIsNull");
        ChildB cB2 = new ChildB("saveChildEntityWithParentAnnotationAndParentIsNull");
        ChildB cB3 = new ChildB("saveChildEntityWithParentAnnotationAndParentIsNull");
        List<ChildB> cBList = Arrays.asList(cB1, cB2, cB3);

        List<ChildB> savedCBList = (List<ChildB>) mongoTemplate.insertAll(cBList);

        Assertions.assertEquals(cBList.size(), savedCBList.size());
        savedCBList.forEach(c -> Assertions.assertNotNull(c.getId()));
    }
}
