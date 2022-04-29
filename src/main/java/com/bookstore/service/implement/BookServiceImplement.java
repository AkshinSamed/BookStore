package com.bookstore.service.implement;

import com.bookstore.model.Author;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class BookServiceImplement implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void saveBook() {
        Scanner input = new Scanner(System.in);
        Author author = new Author();
    }
}
