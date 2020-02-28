package com.dj.ssm.service;

import com.dj.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {


    User findNameAndUserPwd(String  userName, String  userPwd) throws  Exception;
    List<User> findAll() throws Exception;
    User findUserById(Integer id) throws Exception;
    void update(User user) throws Exception;
    User findNameOrPhoneOrEmail(User user) throws Exception;
    void insertUser(User user) throws Exception;

}
