package io.fimataf.ancestry.entities.explicit;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
@TypeAlias("SaveSingleEntityF")
public class SaveSingleEntityF {

    @Id
    private String id;

    private String name;

    private String _saveSingleEntityEId;

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

    public String get_saveSingleEntityEId() {
        return _saveSingleEntityEId;
    }

    public void set_saveSingleEntityEId(String _saveSingleEntityEId) {
        this._saveSingleEntityEId = _saveSingleEntityEId;
    }
}
