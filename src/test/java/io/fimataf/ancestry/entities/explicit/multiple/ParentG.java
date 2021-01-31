package io.fimataf.ancestry.entities.explicit.multiple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 01/02/2021
 */
@Document
@TypeAlias("parentGMulti")
public class ParentG {

    @Id
    private String id;

    private String name;

    private String _childG1Id;

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

    public String get_childG1Id() {
        return _childG1Id;
    }

    public void set_childG1Id(String _childG1Id) {
        this._childG1Id = _childG1Id;
    }
}
