package com.codeup.pettential.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Volunteer")
public class Volunteer {
    @Id @GeneratedValue
    long id;

    @Column
    private String time;

    @Column
    private String date;

    @Column
    private int numberOfVols;

    @ManyToMany(mappedBy = "volunteers")
    private List<User> volunteerUsers;

    @ManyToOne
    @JoinColumn (name = "shel_id")
    private Shelter shelter;

    public Volunteer() {
    }

    public Volunteer(String time, String date, int numberOfVols) {
        this.time = time;
        this.date = date;
        this.numberOfVols = numberOfVols;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfVols() {
        return numberOfVols;
    }

    public void setNumberOfVols(int numberOfVols) {
        this.numberOfVols = numberOfVols;
    }

    public List<User> getVolunteerUsers() {
        return volunteerUsers;
    }

    public void setVolunteerUsers(List<User> volunteerUsers) {
        this.volunteerUsers = volunteerUsers;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
