package com.shzu.shzu.filter;

import com.shzu.shzu.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag= true;
        if(handler instanceof HandlerMethod)
        {
            RoleCheck roleCheck = ((HandlerMethod) handler).getMethod().getAnnotation(RoleCheck.class);
            if(roleCheck!=null) //有权限控制就要
            {
                if (request.getSession().getAttribute("user") == null) {// 没登录就要求登录
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter out=response.getWriter();
                    out.write("{\"type\":\"nosignin\",\"msg\":\"请您先登录!\"}");
                    out.flush();
                    out.close();
                    flag = false;
                }
                else
                {
                    User user = (User) request.getSession().getAttribute("user");
                    if(roleCheck.level()!=user.getUser_role())
                    {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        response.setContentType("text/html; charset=UTF-8");
                        PrintWriter out=response.getWriter();
                        out.write("{\"type\":\"noauth\",\"msg\":\"您没有权限!\"}");
                        out.flush();
                        out.close();
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }
}
