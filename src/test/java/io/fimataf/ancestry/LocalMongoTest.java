package io.fimataf.ancestry;

import io.fimataf.ancestry.config.LocalMongoConfig;
import io.fimataf.ancestry.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fima
 * Created on: 21/01/2021
 */
@SpringBootTest
@ContextConfiguration(classes = {LocalMongoConfig.class})
public class LocalMongoTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        Person person = new Person();
        person.setAge(1);
        person.setName("a");
        mongoTemplate.insert(person);
    }

}
