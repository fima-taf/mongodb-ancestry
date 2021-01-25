package io.fimataf.ancestry.entities.explicit;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
@TypeAlias("childB")
public class ChildB {

    @Id
    private String id;

    private String name;

    private String _parentBId;

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

    public String get_parentBId() {
        return _parentBId;
    }

    public void set_parentBId(String _parentBId) {
        this._parentBId = _parentBId;
    }
}
