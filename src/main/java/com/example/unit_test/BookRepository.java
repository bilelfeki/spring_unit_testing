package com.example.unit_test;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    @Query("select b from Book b where b.Id = ?1 and b.name= ?2")
    Book findByIdAndName(Integer id, String name);
    @Query("select b from Book b where  b.name= ?1")
    Book findByName( String name);
}
