package io.fimataf.ancestry.exceptions;

/**
 * @author fima
 * Created on: 29/01/2021
 */
public class NoParentFoundException extends AncestryMongodbException {

    public NoParentFoundException(String message) {
        super(message);
    }
}
