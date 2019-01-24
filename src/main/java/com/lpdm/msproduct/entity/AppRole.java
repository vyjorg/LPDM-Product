package com.lpdm.msproduct.entity;

import java.util.HashSet;
import java.util.Set;

public class AppRole {

    private int id;
    private String roleName;

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

    @Override
    public String toString() {
        return "AppRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
