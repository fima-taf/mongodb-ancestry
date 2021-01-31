package io.fimataf.ancestry.entities.explicit.single;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 28/01/2021
 */
@Document
@TypeAlias("parentF")
public class ParentF {

    @Id
    private String id;

    private String name;

    private String _childFId;

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

    public String get_childFId() {
        return _childFId;
    }

    public void set_childFId(String _childFId) {
        this._childFId = _childFId;
    }
}
