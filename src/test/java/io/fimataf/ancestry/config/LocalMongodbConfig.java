package io.fimataf.ancestry.config;

import io.fimataf.ancestry.MongodbAncestryEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * @author fima
 * Created on: 21/01/2021
 */
@Configuration
public class LocalMongodbConfig extends AbstractMongoClientConfiguration {

//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/mongotest");
//        MongoClientSettings mongoClientSetting = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//        MongoClient client =  MongoClients.create(mongoClientSetting);
//        return new MongoTemplate(client, "mongotest");
//    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Override
    protected String getDatabaseName() {
        return "mongotest";
    }
}
