package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("childB2Multi")
public class ChildB2 {

    @Id
    private String id;

    private String name;

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
