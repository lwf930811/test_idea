package com.dj.ssm.service.impl;

import com.dj.ssm.mapper.TokenMapper;
import com.dj.ssm.pojo.Token;
import com.dj.ssm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: token
 * @Package: com.dj.ssm.service.impl
 * @ClassName: TokenServiceImpl
 * @Author: dj
 * @Description:
 * @Date: 2020/1/10 17:25
 * @Version: 1.0
 */
@Service
public class TokenServiceImpl  implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;


    @Override
    public Token findByUserId(Integer userId) throws Exception {
        return tokenMapper.findByUserId(userId);
    }

    @Override
    public void insert(Token token) throws Exception {
        tokenMapper.insert(token);
    }

    @Override
    public void update(Token token) throws Exception {
        tokenMapper.update(token);
    }

    @Override
    public Token findByToken(String token) throws Exception {
        return tokenMapper.findByToken(token);
    }
}
