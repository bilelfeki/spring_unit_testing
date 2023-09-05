package com.example.unit_test.mail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepo extends JpaRepository<Mail,Long> {
    List<Mail> findByMailFromAndMailTo(String mailFrom, String mailTo);
}
