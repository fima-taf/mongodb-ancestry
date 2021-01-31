package io.fimataf.ancestry.entities.explicit.single;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("parentG")
public class ParentG {

    @Id
    private String id;

    private String name;

    private String _childGId;

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

    public String get_childGId() {
        return _childGId;
    }

    public void set_childGId(String _childGId) {
        this._childGId = _childGId;
    }
}
