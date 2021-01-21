package io.fimataf.ancestry.entities;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author fima
 * Created on: 21/01/2021
 */
@Document
public class Person {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
