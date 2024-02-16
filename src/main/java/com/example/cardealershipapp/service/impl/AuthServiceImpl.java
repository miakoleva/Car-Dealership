package com.example.cardealershipapp.service.impl;

import com.example.cardealershipapp.model.User;
import com.example.cardealershipapp.model.exceptions.InvalidArgumentsException;
import com.example.cardealershipapp.model.exceptions.InvalidUserCredentialsException;
import com.example.cardealershipapp.repository.UserRepository;
import com.example.cardealershipapp.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(InvalidUserCredentialsException::new);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
