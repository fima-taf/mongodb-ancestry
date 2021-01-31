package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("childC1Multi")
public class ChildC1 {

    @Id
    private String id;

    private String name;

    private String _parentCId;

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

    public String get_parentCId() {
        return _parentCId;
    }

    public void set_parentCId(String _parentCId) {
        this._parentCId = _parentCId;
    }
}
