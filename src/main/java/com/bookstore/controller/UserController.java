package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.model.User;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all employees
    @GetMapping("/user")
    public List<User> getUserList(){
        return userRepository.findAll();
    }


    // create user rest api
    @PostMapping("/add_user")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }



}
