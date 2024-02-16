package com.example.cardealershipapp.service.impl;

import com.example.cardealershipapp.model.Role;
import com.example.cardealershipapp.model.User;
import com.example.cardealershipapp.model.exceptions.*;
import com.example.cardealershipapp.repository.UserRepository;
import com.example.cardealershipapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(InvalidUserIdException::new);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User register(String email, String password, String repeatPassword, String name, String surname, String phone, Role role) {

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new InvalidEmailOrPasswordException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(
            name,
            surname,
            email,
            phone,
            passwordEncoder.encode(password),
            role
        );

        return userRepository.save(user);

    }

    @Override
    public User findUserByEmail(String username) {
        return this.userRepository.findByEmail(username).get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
