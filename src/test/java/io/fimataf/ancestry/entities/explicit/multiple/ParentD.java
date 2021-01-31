package io.fimataf.ancestry.entities.explicit.multiple;

import io.fimataf.ancestry.annotations.Child;
import io.fimataf.ancestry.entities.base.multiple.ChildD1;
import io.fimataf.ancestry.entities.base.multiple.ChildD2;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 31/01/2021
 */
@Document
@TypeAlias("parentDMulti")
public class ParentD {

    @Id
    private String id;

    private String name;

    private String _childD1Id;

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

    public String get_childD1Id() {
        return _childD1Id;
    }

    public void set_childD1Id(String _childD1Id) {
        this._childD1Id = _childD1Id;
    }
}
