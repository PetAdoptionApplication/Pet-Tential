package com.codeup.pettential.models;

import javax.persistence.*;

@Entity
@Table (name = "Preferences")
public class Preferences {

    @Id @GeneratedValue
    long id;

    @Column
    String breed;

    @Column
    int age;

    @Column
    String color;

    @Column
    String sex;

    @Column
    int weight;

    @OneToOne
    private User owner;

}
