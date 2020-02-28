package com.dj.ssm.service;

import com.dj.ssm.pojo.Token;
import org.springframework.dao.DataAccessException;

public interface TokenService {
    Token findByUserId(Integer userId) throws Exception;
    void  insert(Token token) throws  Exception;
    void  update(Token token) throws Exception;
    Token findByToken(String token) throws Exception;
}

