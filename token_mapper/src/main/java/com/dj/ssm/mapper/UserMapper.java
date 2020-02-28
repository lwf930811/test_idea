package com.dj.ssm.mapper;

import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserMapper {
    User findNameAndUserPwd(@Param("userName") String userName, @Param("userPwd") String userPwd) throws DataAccessException;
    List<User> findAll() throws  DataAccessException;
    User findUserById(Integer id) throws DataAccessException;
    void update(User user) throws DataAccessException;
    User findNameOrPhoneOrEmail(User user) throws  DataAccessException;
    void insertUser(User user) throws DataAccessException;
}
