package com.codeup.pettential.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Shelter")
public class Shelter {

    @Id @GeneratedValue
    long id;

    @Column
    String name;

    @Column
    String location;

    @Column
    String number;

    @Column
    int numberOfPets;

    @Column
    String email;

    @OneToMany
    private List<Pet> pets;

    @OneToMany
    private List<Program> programs;

}
