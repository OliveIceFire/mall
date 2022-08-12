package com.example.mall.service.impl;

import com.example.mall.model.dao.UserMapper;
import com.example.mall.model.entity.User;
import com.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }
}
