package io.fimataf.ancestry.entities.base.multiple;

import io.fimataf.ancestry.annotations.Parent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 01/02/2021
 */
@Document
@TypeAlias("childG1Multi")
public class ChildG1 {

    @Id
    private String id;

    private String name;

    @Parent
    private ParentG parentG;

    public ChildG1() {
    }

    public ChildG1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParentG getParentG() {
        return parentG;
    }

    public void setParentG(ParentG parentG) {
        this.parentG = parentG;
    }
}
