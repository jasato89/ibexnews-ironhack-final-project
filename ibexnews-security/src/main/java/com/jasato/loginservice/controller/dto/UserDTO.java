package com.jasato.loginservice.controller.dto;

import com.jasato.loginservice.models.*;

import java.util.*;

public class UserDTO {
    private String email;
    private String password;
    private String name;
    private String dob;
    private String job;
    private Set<IbexCompany> companies = new HashSet<>();


    public UserDTO(String email, String password, String name, String dob, String job, Set<IbexCompany> companies) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.job = job;
        this.companies = companies;
    }

    public UserDTO() {
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

    public Set<IbexCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<IbexCompany> companies) {
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
