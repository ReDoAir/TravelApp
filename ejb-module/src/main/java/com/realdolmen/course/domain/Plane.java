package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NamedQueries(
        {
                @NamedQuery(name="Plane.findAllPlanes",query = "select p from Plane p"),
                @NamedQuery(name="Plane.findPlaneWithCode", query = "select p from Plane p where p.planeCode like :planeCode"),
                @NamedQuery(name="Plane.findAllPlanesWithNumberOfSeatsHigherThan",query = "select p from Plane p where p.numberOfSeats >= :number")
        }
)
public class Plane implements Serializable{

    public static final String FIND_ALL_PLANES="Plane.findAllPlanes",
    FIND_PLANE_WITH_CODE="Plane.findPlaneWithCode",
    FIND_ALL_PLANES_WITH_NUMBER_OF_SEATS_HIGHER_THAN="Plane.findAllPlanesWithNumberOfSeatsHigherThan";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String planeCode;

    @Basic(optional = false)
    private int numberOfSeats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plane(String planeCode, int numberOfSeats) {
        this.planeCode = planeCode;
        this.numberOfSeats = numberOfSeats;
    }

    public Plane() {
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
