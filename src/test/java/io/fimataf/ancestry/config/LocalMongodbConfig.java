package io.fimataf.ancestry.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.fimataf.ancestry.MongodbAncestryEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author fima
 * Created on: 21/01/2021
 */
@EnableMongoRepositories
public class LocalMongodbConfig {

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/mongotest");
        MongoClientSettings mongoClientSetting = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient client =  MongoClients.create(mongoClientSetting);
        return new MongoTemplate(client, "mongotest");
    }

    @Bean
    public MongodbAncestryEventListener mongodbAncestryEventListener () {
        return new MongodbAncestryEventListener();
    }

}
