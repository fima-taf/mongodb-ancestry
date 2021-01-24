package io.fimataf.ancestry.entities.explicit;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 22/01/2021
 */
@Document
@TypeAlias("saveSingleA")
public class SaveSingleEntityA {

    @Id
    private String id;

    private String name;

    private String _saveSingleEntityBId;

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

    public String get_saveSingleEntityBId() {
        return _saveSingleEntityBId;
    }

    public void set_saveSingleEntityBId(String _saveSingleEntityBId) {
        this._saveSingleEntityBId = _saveSingleEntityBId;
    }
}
