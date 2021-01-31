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
@TypeAlias("parentAMulti")
public class ParentA {

    @Id
    private String id;

    private String name;

    @Child
    private ChildA childAFirst;

    @Child
    private ChildA childASecond;

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

    public ChildA getChildAFirst() {
        return childAFirst;
    }

    public void setChildAFirst(ChildA childAFirst) {
        this.childAFirst = childAFirst;
    }

    public ChildA getChildASecond() {
        return childASecond;
    }

    public void setChildASecond(ChildA childASecond) {
        this.childASecond = childASecond;
    }
}
