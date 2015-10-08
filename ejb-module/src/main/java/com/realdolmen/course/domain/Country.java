package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name="Country.findAll",query = "select c from Country c"),
                @NamedQuery(name="Country.findAllByName", query = "select c from Country c where c.name like :name")
        }
)
public class Country implements Serializable {

    public static final String FIND_ALL_COUNTRIES = "Country.findAll",
    FIND_ALL_COUNTRIES_BY_NAME = "Country.findAllByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<Airport>airports;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
