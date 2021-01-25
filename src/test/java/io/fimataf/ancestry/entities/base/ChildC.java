package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Parent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
@TypeAlias("childC")
public class ChildC {

    @Id
    private String id;

    private String name;

    @Parent(keepAfterSave = true)
    private ParentC parentC;

    public ChildC(String name) {
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
