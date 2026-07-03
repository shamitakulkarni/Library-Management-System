package com.library.service;

import com.library.entity.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Login using email
    public Optional<User> loginUser(String email) {
        return userRepository.findByEmail(email);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}