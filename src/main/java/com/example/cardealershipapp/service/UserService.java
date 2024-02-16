package com.example.cardealershipapp.service;

import com.example.cardealershipapp.model.Role;
import com.example.cardealershipapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findById(Long id);
    List<User> findAll();
    User register(String email, String password, String repeatPassword, String name, String surname, String phone, Role role);

    User findUserByEmail(String username);
}
