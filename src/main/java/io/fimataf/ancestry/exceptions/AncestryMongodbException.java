package io.fimataf.ancestry.exceptions;

/**
 * @author fima
 * Created on: 29/01/2021
 */
public class AncestryMongodbException extends RuntimeException {

    public AncestryMongodbException (String message) {
        super(message);
    }
}
