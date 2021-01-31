package io.fimataf.ancestry.entities.base.multiple;

import io.fimataf.ancestry.annotations.AncestryActions;
import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 31/01/2021
 */
@Document
@TypeAlias("parentEMulti")
public class ParentE {

    @Id
    private String id;

    private String name;

    @Child(onChildChange = AncestryActions.DETACH)
    private ChildE1 childE1;

    @Child(onChildChange = AncestryActions.DETACH)
    private ChildE2 childE2;

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

    public ChildE1 getChildE1() {
        return childE1;
    }

    public void setChildE1(ChildE1 childE1) {
        this.childE1 = childE1;
    }

    public ChildE2 getChildE2() {
        return childE2;
    }

    public void setChildE2(ChildE2 childE2) {
        this.childE2 = childE2;
    }
}
