package io.fimataf.ancestry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

/**
 * @author fima
 * Created on: 25/01/2021
 */
//@Configuration
public class MongodbConfig extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "mongotest";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
