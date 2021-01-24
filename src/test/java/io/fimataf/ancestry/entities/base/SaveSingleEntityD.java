package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Parent;
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

    @Parent
    private SaveSingleEntityC saveSingleEntityC;

    public SaveSingleEntityD(String name) {
        this.name = name;
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

    public SaveSingleEntityC getSaveSingleEntityC() {
        return saveSingleEntityC;
    }

    public void setSaveSingleEntityC(SaveSingleEntityC saveSingleEntityC) {
        this.saveSingleEntityC = saveSingleEntityC;
    }
}
