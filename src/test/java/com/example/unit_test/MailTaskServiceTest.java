package com.example.unit_test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

class MailTaskServiceTest {
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
        MailTask mailTask1 =new MailTask(1,"unit testing");
        MailTask mailTask2 =new MailTask(2,"unit testing");
        List<MailTask> mailTasks = new ArrayList<>();
        mailTasks.add(mailTask1);
        mailTasks.add(mailTask2);

        given(bookRepository.findAll()).willReturn(mailTasks);
        assertThat(bookService.calculateLibraryBook()).isGreaterThan(1);
    }

}