package io.fimataf.ancestry.entities.base;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 22/01/2021
 */
@Document
@TypeAlias("childA")
public class ChildA {

    @Id
    private String id;

    @Indexed
    private String name;

    public ChildA(String name) {
        this.name = name;
    }

    public ChildA () {}

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

}
