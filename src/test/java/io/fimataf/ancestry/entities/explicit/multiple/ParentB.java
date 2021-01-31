package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("parentBMulti")
public class ParentB {

    @Id
    private String id;

    private String name;

    private String _childB1Id;

    private String _childB2Id;

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

    public String get_childB1Id() {
        return _childB1Id;
    }

    public void set_childB1Id(String _childB1Id) {
        this._childB1Id = _childB1Id;
    }

    public String get_childB2Id() {
        return _childB2Id;
    }

    public void set_childB2Id(String _childB2Id) {
        this._childB2Id = _childB2Id;
    }
}
