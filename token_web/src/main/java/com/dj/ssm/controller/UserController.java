package com.dj.ssm.controller;

import com.dj.ssm.commons.common.ResultModel;
import com.dj.ssm.commons.common.SystemConstant;
import com.dj.ssm.commons.utils.QiNiuYunUtil;
import com.dj.ssm.commons.utils.UploadAndDown;
import com.dj.ssm.pojo.Token;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.TokenService;
import com.dj.ssm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

/**
 * @ProjectName: token
 * @Package: com.dj.ssm.controller
 * @ClassName: UserController
 * @Author: dj
 * @Description:
 * @Date: 2020/1/10 17:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @RequestMapping("login.rsa")
    public ResultModel<Object> login(String userName, String userPwd,String data) {
        try {
            HashMap<String, Object> map = new HashMap<>();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE,5);
            Token token = new Token();
            map.put("token",uuid);
            token.setToken(uuid).setEndTime(calendar.getTime());
            if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPwd)){
            return new ResultModel<>().error(SystemConstant.NOT_NULL);
        }
            User user = userService.findNameAndUserPwd(userName, userPwd);
            Token token1 = tokenService.findByUserId(user.getId());
            if (user == null){
                return new ResultModel<>().error(SystemConstant.NUMBER_PWD_ERROR);
            }
            if (null == token1) {
                token.setUserId(user.getId());
                tokenService.insert(token);
                return new ResultModel<>().success(map);
            }
            tokenService.update(token);
            return new ResultModel<>().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());

        }
    }
    @RequestMapping("userShow")
    public ResultModel<Object> userShow(Integer page) {
        try {
        Map<String, Object> resultMap = new HashMap<>();
        PageHelper.startPage(page,3);
            List<User> userList = userService.findAll();
            PageInfo<User> pageInfo = new PageInfo<>(userList);
            resultMap.put("userList", pageInfo.getList());
            resultMap.put("total", pageInfo.getPages());
            return new ResultModel<>().success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }

    @RequestMapping("update")
    public ResultModel<Object> update(User user, MultipartFile file, String img) {
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "")
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            //通过inputStream上传文件
            InputStream inputStream = file.getInputStream();
            //调用七牛云工具类中的上传方法
            QiNiuYunUtil.uploadByInputStream(inputStream, fileName);
            //将图片信息保存到数据库中
            user.setImg(fileName);
            userService.update(user);
            QiNiuYunUtil.delFile(img);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }
    @RequestMapping("deleteUser")
    public ResultModel<Object> deleteUser(User user) {
        try {
            user.setIsDel(2);
            userService.update(user);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }
    @RequestMapping("findNameOrPhoneOrEmail")
    public Boolean findNameOrPhoneOrEmail(User user) {
        try {
            User user1 = userService.findNameOrPhoneOrEmail(user);
            return user1 == null ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    @RequestMapping("insertUser")
    public ResultModel<Object> insertUser(User user,MultipartFile file) {
        try {
            String upload = UploadAndDown.upload(file);
            user.setImg(upload);
            userService.insertUser(user);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }
    }
}
