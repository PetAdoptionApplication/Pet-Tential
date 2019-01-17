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
    private int time;

    @Column
    private String breed;

    @ManyToOne
    @JoinColumn (name = "shelter_id")
    private Shelter shelter;

    public Program() {

    }

    public Program (String name, String type, String description, int length, int time, String breed) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.length = length;
        this.time = time;
        this.breed = breed;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
