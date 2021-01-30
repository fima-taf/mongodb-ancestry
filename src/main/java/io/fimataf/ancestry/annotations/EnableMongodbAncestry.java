package io.fimataf.ancestry.annotations;

import io.fimataf.ancestry.config.MongodbAncestryConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fima
 * Created on: 25/01/2021
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MongodbAncestryConfiguration.class)
public @interface EnableMongodbAncestry {
}
