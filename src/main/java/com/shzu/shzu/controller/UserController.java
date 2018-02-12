package com.shzu.shzu.controller;

import com.shzu.shzu.mapper.UserMapper;
import com.shzu.shzu.model.Office;
import com.shzu.shzu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("u")
@CrossOrigin
public class UserController {
    @Autowired
    private UserMapper userMapper;
    /**
     * 获得学院
     * @return
     */
    @RequestMapping("/getXYS.do")
    public List<User> getAll()
    {
        return userMapper.getALL(1);
    }

    /**
     * 获得部门
     * @return
     */
    @RequestMapping("/getBMS.do")
    public List<User> getBMS()
    {
        return userMapper.getALL(3);
    }
    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addBM.do")
    public String addBM(User user)
    {
        try {
            userMapper.addBM(user);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }
    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/add.do")
    public String add(User user)
    {
        try {
            userMapper.addU(user);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }
    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequestMapping("/update.do")
    public String update(User user)
    {
        try {
            userMapper.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping("/del.do")
    public String del(Integer userId)
    {
        try {
            userMapper.del(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }
}
