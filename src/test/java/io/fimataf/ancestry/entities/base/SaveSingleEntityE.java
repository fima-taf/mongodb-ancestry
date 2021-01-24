package io.fimataf.ancestry.entities.base;

import io.fimataf.ancestry.annotations.Child;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 23/01/2021
 */
@Document
public class SaveSingleEntityE {

    @Id
    private String id;

    private String name;

    @Child(linkToChild = true)
    private SaveSingleEntityF saveSingleEntityF;

    public SaveSingleEntityE(String name, SaveSingleEntityF saveSingleEntityF) {
        this.name = name;
        this.saveSingleEntityF = saveSingleEntityF;
    }

    public SaveSingleEntityE() {
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

    public SaveSingleEntityF getSaveSingleEntityF() {
        return saveSingleEntityF;
    }

    public void setSaveSingleEntityF(SaveSingleEntityF saveSingleEntityF) {
        this.saveSingleEntityF = saveSingleEntityF;
    }
}
