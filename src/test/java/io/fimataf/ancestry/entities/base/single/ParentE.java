package io.fimataf.ancestry.entities.base.single;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 27/01/2021
 */
@Document
@TypeAlias("parentE")
public class ParentE {

    @Id
    private String id;

    private String name;

    @Child
    private ChildE childE;

    public ParentE(String name, ChildE childE) {
        this.name = name;
        this.childE = childE;
    }

    public ParentE(String name) {
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

    public ChildE getChildE() {
        return childE;
    }

    public void setChildE(ChildE childE) {
        this.childE = childE;
    }
}
