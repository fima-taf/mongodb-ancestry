package io.fimataf.ancestry.entities.base;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 22/01/2021
 */
@Document
public class SaveSingleEntityB {

    @Id
    private String id;

    private String email;


    public SaveSingleEntityB(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
