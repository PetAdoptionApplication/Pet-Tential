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
    String type;

    @Column
    String description;

    @Column
    int length;

    @Column
    int time;

    @Column
    String breed;

    @ManyToOne
    @JoinColumn (name = "shelter_id")
    private Shelter shelter;
}
