package io.fimataf.ancestry.entities.explicit.multiple;

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

    private String _parentDId;

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

    public String get_parentDId() {
        return _parentDId;
    }

    public void set_parentDId(String _parentDId) {
        this._parentDId = _parentDId;
    }
}
