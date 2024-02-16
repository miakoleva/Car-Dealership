package com.example.cardealershipapp.service;

import com.example.cardealershipapp.model.User;

import java.util.List;

public interface AuthService {

    User login(String email, String password);
    List<User> findAll();
}
