package io.fimataf.ancestry;

import io.fimataf.ancestry.config.LocalMongodbConfig;
import io.fimataf.ancestry.config.MongodbConfig;
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
@ContextConfiguration(classes = {LocalMongodbConfig.class, MongodbConfig.class})
public abstract class LocalMongodbTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
//        Person person = new Person();
//        person.setAge(2);
//        person.setName("Nice person");
//        Car car = new Car();
//        car.setBrand("BMW");
//        person.setCar(car);
////        car.setPerson(person);
//        Person savedPerson = mongoTemplate.insert(person);

    }

}
