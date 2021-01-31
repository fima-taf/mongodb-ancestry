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
@TypeAlias("parentBMulti")
public class ParentB {

    @Id
    private String id;

    private String name;

    @Child
    private ChildB1 childB1;

    @Child
    private ChildB2 childB2;

    public ParentB(String name) {
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

    public ChildB1 getChildB1() {
        return childB1;
    }

    public void setChildB1(ChildB1 childB1) {
        this.childB1 = childB1;
    }

    public ChildB2 getChildB2() {
        return childB2;
    }

    public void setChildB2(ChildB2 childB2) {
        this.childB2 = childB2;
    }
}
