package com.codeup.pettential.models;

import javax.persistence.*;

@Entity
@Table(name = "Application")
public class Application {
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
