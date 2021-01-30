package io.fimataf.ancestry.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fima
 * Created on: 20/01/2021
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Child {

    boolean linkToChild() default false;

    AncestryActions onChildChange() default AncestryActions.DELETE;
}
