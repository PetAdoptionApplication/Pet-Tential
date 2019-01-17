package com.codeup.pettential.models;

import javax.persistence.*;

@Entity
@Table (name = "Pet")
public class Pet {

    @Id @GeneratedValue
    long id;

    @Column
    String breed;

    @Column
    int age;

    @Column
    String color;

    @Column
    String description;

    @Column
    String picture;

    @Column
    String sex;

    @Column
    int weight;

    @ManyToOne
    @JoinColumn (name = "Shelter_id")
    private Shelter shelter;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
}
