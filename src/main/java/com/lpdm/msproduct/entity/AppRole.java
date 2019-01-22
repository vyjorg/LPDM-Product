package com.lpdm.msproduct.entity;

import java.util.HashSet;
import java.util.Set;

public class AppRole {

    private int id;
    private String roleName;
    private Set<UserRole> roles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
