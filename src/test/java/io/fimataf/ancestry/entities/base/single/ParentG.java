package io.fimataf.ancestry.entities.base.single;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("parentG")
public class ParentG {

    @Id
    private String id;

    private String name;

    @Child
    private ChildG childG;

    public ParentG(String name) {
        this.name = name;
    }

    public ParentG(String name, ChildG childG) {
        this.name = name;
        this.childG = childG;
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

    public ChildG getChildG() {
        return childG;
    }

    public void setChildG(ChildG childG) {
        this.childG = childG;
    }
}
