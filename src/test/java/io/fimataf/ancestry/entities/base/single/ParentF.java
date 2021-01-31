package io.fimataf.ancestry.entities.base.single;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 25/01/2021
 */
@Document
@TypeAlias("parentF")
public class ParentF {

    @Id
    private String id;

    private String name;

    @Child(linkToChild = true)
    private ChildF childF;

    public ParentF() {
    }

    public ParentF(String name) {
        this.name = name;
    }

    public ParentF(String name, ChildF childF) {
        this.name = name;
        this.childF = childF;
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

    public ChildF getChildF() {
        return childF;
    }

    public void setChildF(ChildF childF) {
        this.childF = childF;
    }
}
