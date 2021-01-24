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
        SaveSingleEntityB sseb = new SaveSingleEntityB("sam@gmail.com");
        SaveSingleEntityA ssea = new SaveSingleEntityA("Sam", sseb);
        SaveSingleEntityA savedEmp = mongoTemplate.insert(ssea);

        Assertions.assertNotNull(savedEmp.getId());

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("saveSingleEntityB")).is(savedEmp.getSaveSingleEntityB().getId()));
        io.fimataf.ancestry.entities.explicit.SaveSingleEntityA expEmp = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.SaveSingleEntityA.class);
        Assertions.assertNotNull(expEmp.get_saveSingleEntityBId());
        Assertions.assertEquals(savedEmp.getSaveSingleEntityB().getId(), expEmp.get_saveSingleEntityBId());
    }

    @Test
    void saveEntityWithChildAndParentAnnotations () {
        SaveSingleEntityD ssed  = new SaveSingleEntityD("D");
        SaveSingleEntityC ssec = new SaveSingleEntityC("C", ssed);
        SaveSingleEntityC savedSsec = mongoTemplate.insert(ssec);

        Assertions.assertNotNull(savedSsec.getId());

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("saveSingleEntityC")).is(savedSsec.getId()));
        io.fimataf.ancestry.entities.explicit.SaveSingleEntityD expSsed = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.SaveSingleEntityD.class);
        Assertions.assertNotNull(expSsed.get_saveSingleEntityCId());
        Assertions.assertEquals(savedSsec.getId(), expSsed.get_saveSingleEntityCId());

        Assertions.assertNull(savedSsec.getSaveSingleEntityD().getSaveSingleEntityC());
    }

    @Test
    void saveEntityWithChildAndParentAnnotationsAndKeepParent () throws Exception {
        SaveSingleEntityF ssef  = new SaveSingleEntityF("F");
        SaveSingleEntityE ssee = new SaveSingleEntityE("E", ssef);
        SaveSingleEntityE savedSsee = mongoTemplate.insert(ssee);

        Assertions.assertNotNull(savedSsee.getId());

        Query q = new Query();
        q.addCriteria(Criteria.where(AncestryUtils.generateAncestryIdFieldName("saveSingleEntityE")).is(savedSsee.getId()));
        io.fimataf.ancestry.entities.explicit.SaveSingleEntityF expSsef = mongoTemplate.findOne(q, io.fimataf.ancestry.entities.explicit.SaveSingleEntityF.class);
        Assertions.assertNotNull(expSsef.get_saveSingleEntityEId());
        Assertions.assertEquals(savedSsee.getId(), expSsef.get_saveSingleEntityEId());

        Assertions.assertNotNull(savedSsee.getSaveSingleEntityF().getSaveSingleEntityE());
    }

}
