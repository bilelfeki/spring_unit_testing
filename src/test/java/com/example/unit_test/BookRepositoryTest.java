package com.example.unit_test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository underTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void itShouldSaveNewBook(){
        //Given
        Book book=new Book(1,"unit testing");

        //When
        underTest.save(book);
        //Then
        Optional<Book> book1= underTest.findById(1);
        assertThat(book1)
                .isPresent()
                .hasValueSatisfying(b->{
                    assertThat(b).isInstanceOf(Book.class);
                });
    }
    @Test
    public void getBook(){

    }
}
