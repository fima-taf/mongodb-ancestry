package io.fimataf.ancestry.entities.explicit;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("parentE")
public class ParentE {

    private String id;

    private String name;

    private String _childEId;

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

    public String get_childEId() {
        return _childEId;
    }

    public void set_childEId(String _childEId) {
        this._childEId = _childEId;
    }
}
