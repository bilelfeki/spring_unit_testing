package com.example.unit_test;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<MailTask,Integer> {
    @Query("select b from MailTask b where b.Id = ?1 and b.calledClass= ?2")
    MailTask findByIdAndName(Integer id, String name);
    @Query("select b from MailTask b where  b.calledClass= ?1")
    MailTask findByName(String name);
    @Query("select  b from MailTask b where b.calledClass<>?1")
    List<MailTask> findAllMailTaskExcludingQuartz(String Name);
}
