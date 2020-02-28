package com.dj.ssm.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: token
 * @Package: com.dj.ssm.pojo
 * @ClassName: User
 * @Author: dj
 * @Description:
 * @Date: 2020/1/10 17:21
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class User extends BaseDataModel {
    private String userName;
    private String userPwd;
    private String phone;
    private String email;
    private Integer baseId;
    private Integer level;
    private Integer code;
    private Date codeTime;
    private String levelShow;
    private String img;



}
