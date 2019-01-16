package com.codeup.pettential.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "User")
public class User {

    @Id @GeneratedValue
    long id;

    @Column
    String name;

    @Column
    String address;

    @Column
    String number;

    @Column
    String preferences;

    @Column
    String email;

    @Column
    Boolean notifications;

    @Column
    Boolean isShelter;


    @OneToMany (cascade = CascadeType.ALL, mappedBy = "Pet")
    private List<Pet> pets;

    @ManyToMany
    private List<Program> programs;


}
