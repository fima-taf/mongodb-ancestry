package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("parentAMulti")
public class ParentA {

    @Id
    private String id;

    private String name;

    private String _childAFirstId;

    private String _childASecondId;

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

    public String get_childAFirstId() {
        return _childAFirstId;
    }

    public void set_childAFirstId(String _childAFirstId) {
        this._childAFirstId = _childAFirstId;
    }

    public String get_childASecondId() {
        return _childASecondId;
    }

    public void set_childASecondId(String _childASecondId) {
        this._childASecondId = _childASecondId;
    }
}
