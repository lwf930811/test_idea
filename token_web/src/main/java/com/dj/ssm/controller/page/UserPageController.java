package com.dj.ssm.controller.page;

import com.dj.ssm.pojo.User;
import com.dj.ssm.service.TokenService;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: token
 * @Package: com.dj.ssm.controller.page
 * @ClassName: UserPageController
 * @Author: dj
 * @Description:
 * @Date: 2020/1/12 11:54
 * @Version: 1.0
 */
@RequestMapping("/user/")
@Controller
public class UserPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @RequestMapping("toLogin")
    public  String toLogin(){

        return  "user/login";
    }

    @RequestMapping("toShow")
    public  String toShow(String token, Model model){
            model.addAttribute("token",token);
            return  "user/show";
    }
    @RequestMapping("toUpdate")
    public  String toUpdate(String token, Model model, Integer id){
            model.addAttribute("token",token);
        try {
            User user = userService.findUserById(id);
            model.addAttribute("user",user);
            return  "user/update";
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

    @RequestMapping("toRegister")
    public  String toRegister(String token, Model model){
            model.addAttribute("token",token);
            return  "user/register";
        }


}
