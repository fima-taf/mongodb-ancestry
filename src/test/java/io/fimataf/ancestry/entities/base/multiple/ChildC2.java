package io.fimataf.ancestry.entities.base.multiple;

import io.fimataf.ancestry.annotations.Parent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("childC2Multi")
public class ChildC2 {

    @Id
    private String id;

    private String name;

    @Parent
    private ParentC parentC;

    public ChildC2(String name) {
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

    public ParentC getParentC() {
        return parentC;
    }

    public void setParentC(ParentC parentC) {
        this.parentC = parentC;
    }
}
