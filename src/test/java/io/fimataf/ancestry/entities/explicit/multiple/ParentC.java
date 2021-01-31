package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 31/01/2021
 */
@Document
@TypeAlias("parentCMulti")
public class ParentC {

    @Id
    private String id;

    private String name;

    private String _childC1Id;

    private String _childC2Id;

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

    public String get_childC1Id() {
        return _childC1Id;
    }

    public void set_childC1Id(String _childC1Id) {
        this._childC1Id = _childC1Id;
    }

    public String get_childC2Id() {
        return _childC2Id;
    }

    public void set_childC2Id(String _childC2Id) {
        this._childC2Id = _childC2Id;
    }
}
