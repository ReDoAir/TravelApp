package com.realdolmen.course.domain.auth;

import javax.persistence.Entity;

@Entity
public class Employee extends User {

    private boolean admin = false;

    public Employee(boolean admin) {
        if(admin)
        {
            addRole(Role.ADMIN);
        }

        addRole(Role.EMPLOYEE);
    }

    protected Employee(){
        addRole(Role.EMPLOYEE);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
