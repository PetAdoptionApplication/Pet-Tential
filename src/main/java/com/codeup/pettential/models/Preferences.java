package com.codeup.pettential.models;

import com.codeup.pettential.repositories.PreferencesRepository;

import javax.persistence.*;

@Entity
@Table (name = "Preferences")
public class Preferences {

    @Id @GeneratedValue
    private long id;

    @Column 
    private String breed;

    @Column
    private int age;

    @Column
    private String color;

    @Column
    private String sex;

    @Column
    private int weight;

    @OneToOne
    private User owner;

    public Preferences () {

    }

    public Preferences(String breed, int age, String color, String sex, int weight) {
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.sex = sex;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
