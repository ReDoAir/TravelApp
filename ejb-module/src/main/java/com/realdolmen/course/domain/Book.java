package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable{
    public enum Genre {
        fiction, fantasy, thriller, biography
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String title;

    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    /**
     * Used by JPA.
     */
    protected Book() {
    }

    public Book(String title, String author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
