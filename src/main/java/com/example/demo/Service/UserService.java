package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Domain.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.saveUser(user);
    }

    public User getUserById(@PathVariable int id){
        return userRepository.getUserById(id);
    }
    
}
