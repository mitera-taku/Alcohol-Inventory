package com.example.demo.Controller;

import com.example.demo.Domain.User;

import com.example.demo.Service.UserService;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/log")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "HTML/user"; 
    }

    @GetMapping("/login")
    public String login(){
        return  "HTML/login"; 
    }

       // ユーザーを登録
       @PostMapping("/register")
       public String registerUser(@ModelAttribute User user) {
           userService.saveUser(user);
           return "redirect:/main";
       }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
}

