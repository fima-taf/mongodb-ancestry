package io.fimataf.ancestry.entities.base.multiple;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 31/01/2021
 */
@Document
@TypeAlias("parentDMulti")
public class ParentD {

    @Id
    private String id;

    private String name;

    @Child
    private ChildD1 childD1;

    @Child(linkToChild = true)
    private ChildD2 childD2;

    public ParentD(String name) {
        this.name = name;
    }

    public ParentD() {
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

    public ChildD1 getChildD1() {
        return childD1;
    }

    public void setChildD1(ChildD1 childD1) {
        this.childD1 = childD1;
    }

    public ChildD2 getChildD2() {
        return childD2;
    }

    public void setChildD2(ChildD2 childD2) {
        this.childD2 = childD2;
    }
}
