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
    @Sql("classpath:script.sql")
    @Test
    public void itShouldSaveNewBook(){
        //Given
        Book book1=new Book(1,"unit testing");
        Book book2=new Book(2,"unit testing");
        //When
        underTest.save(book1);
        underTest.save(book2);
        //Then
        Optional<Book> book1Db= underTest.findById(1);
        underTest.findByIdAndName(1,"unit testing");
        assertThat(book1Db)
                .isPresent()
                .hasValueSatisfying(b->{
                    assertThat(b).isInstanceOf(Book.class);
                });
    }
    @Test
    public void getBook(){

    }
}
