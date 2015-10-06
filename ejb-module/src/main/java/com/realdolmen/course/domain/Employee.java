package com.realdolmen.course.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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
