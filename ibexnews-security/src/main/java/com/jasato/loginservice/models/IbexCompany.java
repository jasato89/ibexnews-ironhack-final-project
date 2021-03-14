package com.jasato.loginservice.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
public class IbexCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    @ManyToOne
    @JsonIgnore
    private User user;


    public IbexCompany() {
    }

    public IbexCompany(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
