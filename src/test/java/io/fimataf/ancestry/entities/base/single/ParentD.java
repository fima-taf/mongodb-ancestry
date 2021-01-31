package io.fimataf.ancestry.entities.base.single;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.annotations.AncestryActions;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 25/01/2021
 */
@Document
@TypeAlias("parentD")
public class ParentD {

    @Id
    private String id;

    private String name;

    @Child(onChildChange = AncestryActions.DETACH)
    private ChildA childA;

    public ParentD(String name) {
        this.name = name;
    }

    public ParentD(String name, ChildA childA) {
        this.name = name;
        this.childA = childA;
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

    public ChildA getChildA() {
        return childA;
    }

    public void setChildA(ChildA childA) {
        this.childA = childA;
    }
}
