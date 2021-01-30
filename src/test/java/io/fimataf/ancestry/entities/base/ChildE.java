package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Parent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 27/01/2021
 */
@Document
public class ChildE {

    @Id
    private String id;

    private String name;

    @Parent
    private ParentE parentE;

    public ChildE(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParentE getParentE() {
        return parentE;
    }

    public void setParentE(ParentE parentE) {
        this.parentE = parentE;
    }
}
