package io.fimataf.ancestry.entities.explicit;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 30/01/2021
 */
@Document
@TypeAlias("childF")
public class ChildF {

    @Id
    private String id;

    private String name;

    private String _parentFId;

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

    public String get_parentFId() {
        return _parentFId;
    }

    public void set_parentFId(String _parentFId) {
        this._parentFId = _parentFId;
    }
}
