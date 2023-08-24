package com.example.unit_test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookService =new BookService(bookRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateLibraryBook() {
        Book book1=new Book(1,"unit testing");
        Book book2=new Book(2,"unit testing");
        List<Book> books= new ArrayList<>();
        books.add(book1);
        books.add(book2);

        given(bookRepository.findAll()).willReturn(books);
        assertThat(bookService.calculateLibraryBook()).isGreaterThan(1);
    }
}