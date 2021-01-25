package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
public class ParentB {

    @Id
    private String id;

    @Indexed
    private String name;

    @Child(linkToChild = true)
    private ChildB childB;

    public ParentB(String name, ChildB childB) {
        this.name = name;
        this.childB = childB;
    }

    public ParentB() {
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

    public ChildB getChildB() {
        return childB;
    }

    public void setChildB(ChildB childB) {
        this.childB = childB;
    }
}
