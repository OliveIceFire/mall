package com.example.mall.controller;

import com.example.mall.model.entity.User;
import com.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public User getUserInfo() {
        return userService.getUser();
    }
}
