package com.shzu.shzu.controller;

import com.shzu.shzu.mapper.UserMapper;
import com.shzu.shzu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("hello.do")
    public String Hello()
    {
        return "你好";
    }

    /**
     * 登录
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("login.do")
    public String login(HttpServletRequest request, User user, HttpServletResponse response,Model model)
    {
        User user1 = userMapper.getUser(user);
        if(user1==null)
        {
            return "ERROR";
        }
        request.getSession().setAttribute("user",user1);
        try{
        switch (user1.getUser_role())
        {
            case 1:response.sendRedirect("/web/academy.html");break;
            case 2:response.sendRedirect("/web/expert.html");break;
            case 3:response.sendRedirect("/web/department.html");break;
            case 4:response.sendRedirect("/web/admin.html");break;
        }}
        catch (Exception e)
        {
            return e.getMessage();
        }
        return null;
    }
    /**
     * 获得用户ID
     * @param request
     * @return
     */
    @RequestMapping("getUserId.do")
    public String getUserId(HttpServletRequest request)
    {

        User cuUser = (User) request.getSession().getAttribute("user");
        if(cuUser==null)
        {
            return "ERROR";
        }
        return cuUser.getUser_id().toString();

    }
    /**
     * 注销
     * @return
     */
    @RequestMapping("logout.do")
    public String logout(HttpSession session,HttpServletResponse response) throws IOException {
        response.sendRedirect("/web/login.html");
        session.setAttribute("user",null);
        return "退出成功";
    }
}

