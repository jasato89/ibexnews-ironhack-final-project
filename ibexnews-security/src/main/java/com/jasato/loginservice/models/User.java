package com.jasato.loginservice.models;

import com.fasterxml.jackson.annotation.*;
import com.jasato.loginservice.enums.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(columnDefinition = "TEXT")
    private String name;
    private LocalDate dob;
    private String job;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "roleOwner")
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<IbexCompany> companies = new HashSet<>();

    public User(String username, String password, String name, LocalDate dob, String job) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.job = job;
    }

    public User() {
    }

    public Set<IbexCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<IbexCompany> companies) {
        this.companies = companies;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : this.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleType()));
        }

        return authorities;


    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public void addRole(Roles role) {
       roles.add(new Role(role.toString(), this));
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String picture) {
        this.name = picture;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setRole(String role) {
        roles.add(new Role(role, this));

    }
    public void addIbexCompany(String ibexCompany) {
        companies.add(new IbexCompany(ibexCompany, this));
    }

    public void removeIbexCompany(IbexCompany ibexCompany) {
        companies.remove(ibexCompany);
    }


}
