package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Parent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
@TypeAlias("childB")
public class ChildB {

    @Id
    private String id;

    private String name;

    @Parent
    private ParentB parentB;

    public ChildB(String name) {
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

    public ParentB getParentB() {
        return parentB;
    }

    public void setParentB(ParentB parentB) {
        this.parentB = parentB;
    }
}
