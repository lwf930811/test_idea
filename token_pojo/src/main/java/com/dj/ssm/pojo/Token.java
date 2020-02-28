package com.dj.ssm.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ProjectName: token
 * @Package: com.dj.ssm.pojo
 * @ClassName: Token
 * @Author: dj
 * @Description:
 * @Date: 2020/1/10 17:22
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class Token {
    private  Integer id;
    private  Integer userId;
    private  String  token;
    private Date endTime;

}
