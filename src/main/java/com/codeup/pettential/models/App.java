package com.codeup.pettential.models;

import javax.persistence.*;

@Entity
@Table(name = "App")
public class App {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private Boolean approvalStatus;

    @OneToOne
    private User user;

    @OneToOne
    private Pet pet;

    @OneToOne
    private Shelter shelter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
