package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 01/02/2021
 */
@Document
@TypeAlias("childG2Multi")
public class ChildG2 {

    @Id
    private String id;

    private String name;

    private String _parentGId;

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

    public String get_parentGId() {
        return _parentGId;
    }

    public void set_parentGId(String _parentGId) {
        this._parentGId = _parentGId;
    }
}
