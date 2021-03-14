package com.jasato.ibexnews.feign.controller.dtos;

import java.util.*;

public class UserDTO {
    private String email;
    private String password;
    private String name;
    private String dob;
    private String job;
    private Set<UserIbexDTO> companies = new HashSet<>();


    public UserDTO(String email, String password, String name, String dob, String job) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.job = job;
    }

    public UserDTO() {
    }

    public void setCompanies(Set<UserIbexDTO> companies) {
        this.companies = companies;
    }

    public Set<UserIbexDTO> getCompanies() {
        return companies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
