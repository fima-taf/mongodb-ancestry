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
@TypeAlias("parentFMulti")
public class ParentF {

    @Id
    private String id;

    private String name;

    @Child(onChildChange = AncestryActions.DETACH, linkToChild = true)
    private ChildF1 childF1;

    @Child(onChildChange = AncestryActions.DETACH, linkToChild = true)
    private ChildF2 childF2;

    public ParentF(String name) {
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

    public ChildF1 getChildF1() {
        return childF1;
    }

    public void setChildF1(ChildF1 childF1) {
        this.childF1 = childF1;
    }

    public ChildF2 getChildF2() {
        return childF2;
    }

    public void setChildF2(ChildF2 childF2) {
        this.childF2 = childF2;
    }
}
