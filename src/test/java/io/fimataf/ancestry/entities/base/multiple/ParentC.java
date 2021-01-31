package io.fimataf.ancestry.entities.base.multiple;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("parentCMulti")
public class ParentC {

    @Id
    private String id;

    private String name;

    @Child(linkToChild = true)
    private ChildC1 childC1;

    @Child(linkToChild = true)
    private ChildC2 childC2;

    public ParentC(String name) {
        this.name = name;
    }

    public ParentC() {
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

    public ChildC1 getChildC1() {
        return childC1;
    }

    public void setChildC1(ChildC1 childC1) {
        this.childC1 = childC1;
    }

    public ChildC2 getChildC2() {
        return childC2;
    }

    public void setChildC2(ChildC2 childC2) {
        this.childC2 = childC2;
    }
}
