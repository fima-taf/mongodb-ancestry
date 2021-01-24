package io.fimataf.ancestry.entities.explicit;

import io.fimataf.ancestry.entities.base.SaveSingleEntityC;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
@TypeAlias("SaveSingleEntityD")
public class SaveSingleEntityD {

    @Id
    private String id;

    private String name;

    private String _saveSingleEntityCId;

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

    public String get_saveSingleEntityCId() {
        return _saveSingleEntityCId;
    }

    public void set_saveSingleEntityCId(String _saveSingleEntityCId) {
        this._saveSingleEntityCId = _saveSingleEntityCId;
    }
}
