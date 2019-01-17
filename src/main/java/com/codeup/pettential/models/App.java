package com.codeup.pettential.models;

import javax.persistence.*;

@Entity
@Table(name = "App")
public class App {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private User user;

    @OneToOne
    private Pet pet;

    @OneToOne
    private Shelter shelter;
}
