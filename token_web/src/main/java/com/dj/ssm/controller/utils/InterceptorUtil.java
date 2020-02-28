package com.dj.ssm.controller.utils;

import com.dj.ssm.pojo.Token;
import com.dj.ssm.service.TokenService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * @ProjectName: token
 * @Package: com.dj.ssm.commons.utils
 * @ClassName: InterceptorUtil
 * @Author: dj
 * @Description:
 * @Date: 2020/1/10 17:50
 * @Version: 1.0
 */
public class InterceptorUtil implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = request.getParameter("token");
        if (StringUtil.isEmpty(uuid)) {
            response.sendRedirect(request.getContextPath()+"/user/toLogin");
            return false;
        }
        Token token = tokenService.findByToken(uuid);
        if (null == token){
            response.sendRedirect(request.getContextPath()+"/user/toLogin");
            return false;

        }
       if ( System.currentTimeMillis() > token.getEndTime().getTime()){
           response.sendRedirect(request.getContextPath()+"/user/toLogin");
           return false;
       }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,5);
        Token token1 = new Token().setToken(uuid).setEndTime(calendar.getTime());
        tokenService.update(token1);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
