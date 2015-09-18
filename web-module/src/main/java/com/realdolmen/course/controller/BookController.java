package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Book;
import com.realdolmen.course.persistence.BookRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostLoad;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class BookController {
    @Inject
    BookRepository repository;

    private List<Book> books = new ArrayList<>();

    private String bookTitle;

    public List<Book> getBooks(){
        return books;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public void remove(int bookId) {
        repository.remove(bookId);
    }

    public String getBookTitle(){
        return bookTitle;
    }

    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }

    public List<Book> filterBooks() {
        if(bookTitle != null) {
            return repository.findBooksByTitle(bookTitle);
        }else {
            return getAllBooks();
        }
    }
}
