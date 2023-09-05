package com.example.unit_test;

import com.example.unit_test.mail.Mail;
import com.example.unit_test.mail.MailRepo;
import com.example.unit_test.task.Task;
import com.example.unit_test.task.TaskRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskHandler {
    TaskRepo taskRepo;
    MailRepo mailRepo;
    public void aliCreateAMail(){
        Mail mail = new Mail();
        mail.setMailFrom("bilel");
        mail.setMailTo("aziz");
        mailRepo.save(mail);
    }
    public void salahCreateAMail(){
        Mail mail = new Mail();
        mail.setMailFrom("salah");
        mail.setMailTo("3abla");
        mailRepo.save(mail);
    }
    public void firstTask(){
        //kol mail ketbou salah bech nesn3oulou 2 tasks
        //hint: mart salah 3abla, salah yeb3ath ken l martou
        List<Mail> salahMails= mailRepo.findByMailFromAndMailTo("salah","3abla");

        for (Mail mail :  salahMails){
            Task task1 = new Task();
            Task task2 = new Task();
            task1.setTaskDateCreation(new Date());
            task1.setMail(mail);
            task2.setTaskDateCreation(new Date());
            task2.setMail(mail);
            taskRepo.save(task1);
            taskRepo.save(task2);
        }

    }
    @Bean
    public void call(){
        firstTask();
    }
}
