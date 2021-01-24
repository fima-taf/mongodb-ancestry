package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Child;
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

    @Child
    private SaveSingleEntityB saveSingleEntityB;

    public SaveSingleEntityA(String name, SaveSingleEntityB saveSingleEntityB) {
        this.name = name;
        this.saveSingleEntityB = saveSingleEntityB;
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

    public SaveSingleEntityB getSaveSingleEntityB() {
        return saveSingleEntityB;
    }

    public void setSaveSingleEntityB(SaveSingleEntityB saveSingleEntityB) {
        this.saveSingleEntityB = saveSingleEntityB;
    }
}
