package com.jasato.loginservice.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String roleType;
    @ManyToOne
    private User roleOwner;

    public Role( String role, User user) {
        this.roleType = role;
        this.roleOwner = user;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String role) {
        this.roleType = role;
    }

    public User getRoleOwner() {
        return roleOwner;
    }

    public void setRoleOwner(User user) {
        this.roleOwner = user;
    }
}
