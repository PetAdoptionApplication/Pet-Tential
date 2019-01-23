package com.codeup.pettential.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "User")
public class User {

    @Id @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String number;

    @Column
    private String email;

    @Column
    private Boolean notifications;

    @Column
    private boolean isShelter;

    @OneToMany
    private List<Pet> pets;

    @ManyToMany
    private List<Program> programs;

    @OneToOne
    private Preferences preferences;


    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        name = copy.name;
        address = copy.address;
        number = copy.number;
        preferences = copy.preferences;
        notifications = copy.notifications;
        isShelter = copy.isShelter;
        programs = copy.programs;
    }

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    public Boolean getIsShelter() {
        return isShelter;
    }

    public void setIsShelter(Boolean shelter) {
        isShelter = shelter;
    }


    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public boolean isShelter() {
        return isShelter;
    }

    public void setShelter(boolean shelter) {
        isShelter = shelter;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }


    public Preferences getPreferences() {
        return preferences;
    }
}
