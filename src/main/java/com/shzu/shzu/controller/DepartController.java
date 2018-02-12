package com.shzu.shzu.controller;

import com.shzu.shzu.mapper.CePinMapper;
import com.shzu.shzu.mapper.FormMapper;
import com.shzu.shzu.model.Form;
import com.shzu.shzu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("depart")
public class DepartController {

    @Autowired
    private FormMapper departMapper;
    @Autowired
    private CePinMapper cePinMapper;

    @RequestMapping("/add.do")
    public String add(Form form, HttpSession session)
    {
        User cuUser = (User) session.getAttribute("user");
        if(cuUser==null)
            return "未登录";
        if(new Date().after(cePinMapper.getDeadDate(form.getForm_cp_id())))
            return "已过评测时间！提交失效！";
        form.setForm_depart_id(cuUser.getUser_id());
        departMapper.add(form);
        return "OK";
    }
}
