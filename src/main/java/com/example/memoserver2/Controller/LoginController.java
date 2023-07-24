package com.example.memoserver2.Controller;

import com.example.memoserver2.Model.User;
import com.example.memoserver2.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/getUser")
    private User sendUser(@RequestBody() User user){
        return userRepository.findById(user.getId()).get();
    }
}
