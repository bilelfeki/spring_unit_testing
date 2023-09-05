package com.example.unit_test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class MailTaskRepositoryTest {

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
        MailTask mailTask1 =new MailTask(1,"unit testing");
        MailTask mailTask2 =new MailTask(2,"unit testing");
        //When
        underTest.save(mailTask1);
        underTest.save(mailTask2);
        //Then
        Optional<MailTask> book1Db= underTest.findById(1);
        underTest.findByIdAndName(1,"unit testing");
        assertThat(book1Db)
                .isPresent()
                .hasValueSatisfying(b->{
                    assertThat(b).isInstanceOf(MailTask.class);
                });
    }
    @Test
    public void getBook(){
        MailTask mailTask1 =new MailTask(1,"unit testing",QuartzStatus.OK,0,false,false,false);
        MailTask mailTask2 =new MailTask(2,"QuartzJob.java",QuartzStatus.FAILED,0,false,false,false);
        //When
        underTest.save(mailTask1);
        underTest.save(mailTask2);
        //Then
        List<MailTask> mailTasks = underTest.findAllMailTaskExcludingQuartz("QuartzJob.java");
        for (MailTask mailTask : mailTasks){

            if(mailTask.getExecutionCount()<3){
                mailTask.setExecutionCount(mailTask.getExecutionCount()+1);
                if(mailTask.isSent()&& mailTask.isOpened()){
                    mailTask.setQuartzStatus(QuartzStatus.OK);
                } else if (mailTask.isBounced()) {
                    mailTask.setQuartzStatus(QuartzStatus.OK);
                }
                underTest.save(mailTask);
            }
        }
        List<MailTask> booksafter = underTest.findAllMailTaskExcludingQuartz("ok");
    }
}
