package com.codeup.pettential.models;

import javax.persistence.*;

@Entity
@Table (name = "Pet")
public class Pet {

    @Id @GeneratedValue
    private long id;

    @Column
    private String breed;

    @Column
    private int age;

    @Column
    private String color;

    @Column
    private String description;

    @Column
    private String picture;

    @Column
    private String sex;

    @Column
    private int weight;

    @ManyToOne
    @JoinColumn (name = "Shelter_id")
    private Shelter shelter;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Pet () {

    }

    public Pet (String breed, int age, String color, String description, String picture, String sex, int weight) {
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.description = description;
        this.picture = picture;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
