package com.realdolmen.course.domain.auth;

import javax.persistence.Entity;

@Entity
public class Employee extends User {

    private boolean admin = false;

    public Employee(boolean admin) {
        this.admin = admin;
    }

    protected Employee(){

    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
