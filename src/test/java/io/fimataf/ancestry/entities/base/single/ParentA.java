package io.fimataf.ancestry.entities.base.single;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 22/01/2021
 */

@Document
@TypeAlias("parentA")
public class ParentA {

    @Id
    private String id;

    private String name;

    @Child
    private ChildA childA;

    public ParentA(String name, ChildA childA) {
        this.name = name;
        this.childA = childA;
    }

    public ParentA(String name) {
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

    public ChildA getChildA() {
        return childA;
    }

    public void setChildA(ChildA childA) {
        this.childA = childA;
    }
}
