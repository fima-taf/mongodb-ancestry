package io.fimataf.ancestry;

import io.fimataf.ancestry.annotations.EnableMongodbAncestry;
import io.fimataf.ancestry.config.LocalMongodbConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author fima
 * Created on: 21/01/2021
 */
@SpringBootTest
@ContextConfiguration(classes = {LocalMongodbConfig.class})
@EnableMongodbAncestry
public abstract class LocalMongodbTest {

    @Autowired
    protected MongoTemplate mongoTemplate;


}
