package com.codeup.pettential.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Shelter")
public class Shelter {

    @Id @GeneratedValue
    long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private String number;

    @Column
    private int numberOfPets;

    @Column
    private String email;

    @OneToMany
    private List<Pet> pets;

    @OneToMany
    private List<Program> programs;

    @OneToMany
    private List<App> applicants;

    public Shelter() {

    }

    public Shelter (String name, String location, String number, int numberOfPets, String email) {
        this.name = name;
        this.location = location;
        this.number = number;
        this.numberOfPets = numberOfPets;
        this.email = email;
    }

    public long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getNumberOfPets() {
        return numberOfPets;
    }

    public void setNumberOfPets(int numberOfPets) {
        this.numberOfPets = numberOfPets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pet> getPets(){
        return pets;
    }

    public List<Program> getPrograms(){
        return programs;
    }
    public List<App> getApplicants(){
        return applicants;
    }
}
