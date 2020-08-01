package com.myData.analyzer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myData.analyzer.entities.User;
import com.myData.analyzer.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }
    
    public User getUser(String username){
        return repo.findByUsername(username).orElse(null);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

}
