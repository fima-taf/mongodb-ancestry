package io.fimataf.ancestry.entities.explicit.single;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 22/01/2021
 */
@Document
@TypeAlias("parentD")
public class ParentD {

    @Id
    private String id;

    private String name;

//    @Field("_other-nameId")
    private String _childAId;

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

    public String get_childAId() {
        return _childAId;
    }

    public void set_childAId(String _childAId) {
        this._childAId = _childAId;
    }
}
