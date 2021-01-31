package io.fimataf.ancestry.entities.explicit.single;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 28/01/2021
 */
@Document
@TypeAlias("parentB")
public class ParentB {

    @Id
    private String id;

    private String name;

    private String _childBId;

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

    public String get_childBId() {
        return _childBId;
    }

    public void set_childBId(String _childBId) {
        this._childBId = _childBId;
    }
}
