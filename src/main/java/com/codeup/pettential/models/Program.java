package com.codeup.pettential.models;

import org.hibernate.annotations.AnyMetaDefs;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Program")
public class Program {

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (
            name = "program_users",
            joinColumns = {@JoinColumn(name ="user_id")},
            inverseJoinColumns = {@JoinColumn(name="program_id")}
    )
    private List<User> programUsers;

    @Id @GeneratedValue
    long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String description;

    @Column
    private int length;

    @Column
    private String time;

    @Column
    private String petType;

    @ManyToOne
    @JoinColumn (name = "shelter_id")
    private Shelter shelter;

    public Program() {

    }

    public Program (String name, String type, String description, int length, String time, String PetType) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.length = length;
        this.time = time;
        this.petType = PetType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getProgramUsers() {
        return programUsers;
    }

    public void setProgramUsers(List<User> programUsers) {
        this.programUsers = programUsers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
