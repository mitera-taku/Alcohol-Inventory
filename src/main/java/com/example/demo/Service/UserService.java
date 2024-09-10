package com.example.demo.Service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Domain.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    // @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }
}

