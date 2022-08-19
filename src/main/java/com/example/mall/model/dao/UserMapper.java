package com.example.mall.model.dao;

import com.example.mall.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //新增
    User selectByName(String username);
    User selectLogin(@Param("username") String username, @Param("password") String password);

    User selectOneByEmailAddress(String emailAddress);
}