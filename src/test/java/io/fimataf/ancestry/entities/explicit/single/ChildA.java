package io.fimataf.ancestry.entities.explicit.single;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 26/01/2021
 */
@Document
@TypeAlias("childA")
public class ChildA {

    @Id
    private String id;

    private String name;

    private String _parentAId;

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

    public String get_parentAId() {
        return _parentAId;
    }

    public void set_parentAId(String _parentAId) {
        this._parentAId = _parentAId;
    }
}
