package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.annotations.AncestryActions;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
public class ParentC {

    @Id
    private String id;

    private String name;

    @Child(linkToChild = true, onChildChange = AncestryActions.DETACH)
    private ChildC childC;

    public ParentC(String name, ChildC childC) {
        this.name = name;
        this.childC = childC;
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

    public ChildC getChildC() {
        return childC;
    }

    public void setChildC(ChildC childC) {
        this.childC = childC;
    }
}
