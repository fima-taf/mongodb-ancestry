package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
public class SaveSingleEntityC {

    @Id
    private String id;

    private String name;

    @Child(linkToChild = true)
    private SaveSingleEntityD saveSingleEntityD;

    public SaveSingleEntityC(String name, SaveSingleEntityD saveSingleEntityD) {
        this.name = name;
        this.saveSingleEntityD = saveSingleEntityD;
    }

    public SaveSingleEntityC() {
    }

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

    public SaveSingleEntityD getSaveSingleEntityD() {
        return saveSingleEntityD;
    }

    public void setSaveSingleEntityD(SaveSingleEntityD saveSingleEntityD) {
        this.saveSingleEntityD = saveSingleEntityD;
    }
}
