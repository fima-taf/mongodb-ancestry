package io.fimataf.ancestry.entities.base.multiple;

import io.fimataf.ancestry.annotations.AncestryActions;
import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 01/02/2021
 */
@Document
@TypeAlias("parentGMulti")
public class ParentG {

    @Id
    private String id;

    private String name;

    @Child(onChildChange = AncestryActions.DETACH)
    private ChildG1 childG1;

    @Child(linkToChild = true, onChildChange = AncestryActions.DETACH)
    private ChildG2 childG2;

    public ParentG(String name) {
        this.name = name;
    }

    public ParentG() {
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

    public ChildG1 getChildG1() {
        return childG1;
    }

    public void setChildG1(ChildG1 childG1) {
        this.childG1 = childG1;
    }

    public ChildG2 getChildG2() {
        return childG2;
    }

    public void setChildG2(ChildG2 childG2) {
        this.childG2 = childG2;
    }
}
