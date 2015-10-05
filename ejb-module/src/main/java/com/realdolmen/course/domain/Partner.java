package com.realdolmen.course.domain;



import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Partner extends User {

    private String airlines;

    public Partner(String airlines) {
        this.airlines = airlines;
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.PARTNER);
        this.setRoles(roleList);
    }

    public Partner(){

    }

    public String getAirlines() {
        return airlines;
    }

    public void setAirlines(String airlines) {
        this.airlines = airlines;
    }
}
