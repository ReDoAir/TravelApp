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
        List<Role> roleList = new ArrayList<>();

        if(admin) {
            roleList.add(Role.ADMIN);
        }else{
            roleList.add(Role.EMPLOYEE);
        }

        this.setRoles(roleList);
    }

    public Employee(){

    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
