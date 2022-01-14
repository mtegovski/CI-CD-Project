package mk.finki.ukim.wp.lab.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserFullname implements Serializable {

    private String name;

    private String surname;

    public UserFullname() {

    }

    public UserFullname(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
