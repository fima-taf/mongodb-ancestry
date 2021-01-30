package io.fimataf.ancestry.config;

import io.fimataf.ancestry.MongodbAncestryEventListener;
import io.fimataf.ancestry.repositories.DBActions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fima
 * Created on: 25/01/2021
 */
@Configuration
public class MongodbAncestryConfiguration {

    @Bean
    public DBActions dbActions () { return new DBActions(); }

    @Bean
    public MongodbAncestryEventListener mongodbAncestryEventListener () {
        return new MongodbAncestryEventListener();
    }
}
