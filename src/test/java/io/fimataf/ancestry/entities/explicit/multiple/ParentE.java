package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 31/01/2021
 */
@Document
@TypeAlias("parentEMulti")
public class ParentE {

    @Id
    private String id;

    private String name;

    private String _childE1Id;

    private String _childE2Id;

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

    public String get_childE1Id() {
        return _childE1Id;
    }

    public void set_childE1Id(String _childE1Id) {
        this._childE1Id = _childE1Id;
    }

    public String get_childE2Id() {
        return _childE2Id;
    }

    public void set_childE2Id(String _childE2Id) {
        this._childE2Id = _childE2Id;
    }
}
