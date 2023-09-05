package com.example.unit_test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
@AllArgsConstructor
public class BookService {
    BookRepository bookRepository;

    public Integer calculateLibraryBook() {
        int i=0;
        Iterator<MailTask> iterator = bookRepository.findAll().iterator();
        while (iterator.hasNext()) {
            iterator.next();
            i++;
        }
        return i;
    }
    public void findAllBooksWithoutName(){
        bookRepository.findAllMailTaskExcludingQuartz("ok");

    }

}
