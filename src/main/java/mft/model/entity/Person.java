package mft.model.entity;


import lombok.experimental.SuperBuilder;
import mft.controller.exception.InvalidPersonDataException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.regex.Pattern;

@SuperBuilder
public class Person implements Serializable {
//todo : setter validator ...
    private int id;
    private String name;
    private String family;
    private String username;
    private String password;
    private LocalDate birthDate;
    private String phoneNumber;


    public Person() {
    }

    public Person(int id, String name, String family, String username, String password, LocalDate birthDate, String phoneNumber) throws InvalidPersonDataException {
        setId(id);
        setName(name);
        setFamily(family);
        setUsername(username);
        setPassword(password);
        this.password = password;
        this.birthDate = birthDate;
    }



    public Person(String name, String family, String username, String password, LocalDate birthDate, String phoneNumber) throws InvalidPersonDataException {
        setName(name);
        setFamily(family);
        setUsername(username);
        setPassword(password);
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidPersonDataException {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", name)) {
            this.name = name;
        } else {
            throw new InvalidPersonDataException("invalid");
        }
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) throws InvalidPersonDataException {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", family)) {
            this.family = family;
        } else {
            throw new InvalidPersonDataException("Invalid Family !!!");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InvalidPersonDataException {

        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", username)) {
            this.username = username;

        } else {
            throw new InvalidPersonDataException("Invalid Username!!!");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InvalidPersonDataException {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", password)) {
            this.password = password;
        } else {
            throw new InvalidPersonDataException("Invalid Password !!!");
        }

    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}



