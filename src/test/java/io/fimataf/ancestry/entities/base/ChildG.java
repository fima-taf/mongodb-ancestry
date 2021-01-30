package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.AncestryActions;
import io.fimataf.ancestry.annotations.Parent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
public class ChildG {

    @Id
    private String id;

    private String name;

    @Parent
    private ParentG parentG;

    public ChildG(String name) {
        this.name = name;
    }

    public ChildG() {
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

    public ParentG getParentG() {
        return parentG;
    }

    public void setParentG(ParentG parentG) {
        this.parentG = parentG;
    }
}
