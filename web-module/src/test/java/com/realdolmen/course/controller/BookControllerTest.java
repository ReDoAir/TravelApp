package com.realdolmen.course.controller;

import com.realdolmen.course.persistence.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest extends Mockito {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController controller = new BookController();

    @Test
    public void getAllBooksDelegatesToBookRepository() throws Exception {
        controller.getAllBooks();
        verify(bookRepository).findAll();
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void removeDelegatesToBookRepository() throws Exception {
        controller.remove(1507);
        verify(bookRepository).remove(1507);
        verifyNoMoreInteractions(bookRepository);
    }
}
