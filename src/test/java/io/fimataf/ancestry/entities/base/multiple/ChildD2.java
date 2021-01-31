package io.fimataf.ancestry.entities.base.multiple;

import io.fimataf.ancestry.annotations.Parent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 31/01/2021
 */
@Document
@TypeAlias("childD2Multi")
public class ChildD2 {

    @Id
    private String id;

    private String name;

    @Parent
    private ParentD parentD;

    public ChildD2(String name) {
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

    public ParentD getParentD() {
        return parentD;
    }

    public void setParentD(ParentD parentD) {
        this.parentD = parentD;
    }
}
