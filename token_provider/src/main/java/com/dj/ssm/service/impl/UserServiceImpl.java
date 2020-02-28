package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.UserMapper;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: token
 * @Package: com.dj.ssm.service.impl
 * @ClassName: UserServiceImpl
 * @Author: dj
 * @Description:
 * @Date: 2020/1/10 17:24
 * @Version: 1.0
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findNameAndUserPwd(String userName, String userPwd) throws Exception {
        return userMapper.findNameAndUserPwd(userName, userPwd);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userMapper.findAll();
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        return userMapper.findUserById(id);
    }

    @Override
    public void update(User user) throws Exception {
        userMapper.update(user);
    }

    @Override
    public User findNameOrPhoneOrEmail(User user) throws Exception {
        return userMapper.findNameOrPhoneOrEmail(user);
    }

    @Override
    public void insertUser(User user) throws Exception {
        userMapper.insertUser(user);
    }
}
