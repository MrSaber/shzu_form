package com.shzu.shzu.controller;

import com.shzu.shzu.mapper.CePinMapper;
import com.shzu.shzu.mapper.FormMapper;
import com.shzu.shzu.mapper.UserMapper;
import com.shzu.shzu.model.Form;
import com.shzu.shzu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("form")
public class AcademyController {
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private CePinMapper cePinMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加新的自评表
     * @param form
     * @param session
     * @return
     */
    //开启事物处理
    //@Transactional
    @RequestMapping("/add.do")
    public String add(Form form,HttpSession session)
    {
        //验证是否允许提交
        User cuUser= (User) session.getAttribute("user");
        if(cuUser==null)
            return "当前用户未登录！";
        if(new Date().after(cePinMapper.getDeadDate(form.getForm_cp_id())))
            return "已过评测时间！提交失效！";
        if(userMapper.isAllow(cuUser.getUser_office())==0)
            return "一次测评只允许提交一次，请联系管理员重开权限！";
        //判断表单是否添加了UserId
        if(form.getForm_user_id()==null)
            form.setForm_user_id(cuUser.getUser_id());
        if(form.getForm_office_id()==null)
            form.setForm_office_id(cuUser.getOf_id());
        //添加入库
        form.setForm_depart_id(-1);
        try{
            formMapper.add(form);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  e.getMessage();
        }
        return "添加成功";
    }

    @RequestMapping("/save.do")
    public String save(Form form)
    {
        User cuUser = (User) session.getAttribute("user");
        //判断表单是否添加了UserId
        form.setForm_user_id(cuUser.getUser_id());
        if(form.getForm_office_id()==null)
            form.setForm_office_id(cuUser.getOf_id());
        //添加入库
        form.setForm_depart_id(-1);
        try{
            formMapper.save(form);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  e.getMessage();
        }
        return "保存成功";
    }
    /**
     * 获得用户的所有自评表
     * @return
     */
    @RequestMapping("/get.do")
    public Form get(Integer cpId)
    {
        User user= (User) session.getAttribute("user");
        return formMapper.getByTJ(cpId,user.getUser_office());
    }

    @RequestMapping("/getSave.do")
    public Form getSave(Integer cpId)
    {
        User user= (User) session.getAttribute("user");
        return formMapper.getSave(cpId,user.getUser_office());
    }

    @RequestMapping("/getSaveZJ.do")
    public Form getSaveZJ(Integer cpId,Integer ofId)
    {
        return formMapper.getSaveZJ(cpId,ofId);
    }
    /**
     * 获得最近一次的自评表
     * @param userId
     * @return
     */
    @RequestMapping("/getLast.do")
    public Form getLast(Integer userId)
    {
        return formMapper.getLast(userId);
    }

    /**
     * 是否为非教学单位
     */
    @RequestMapping("/isNoEdu.do")
    public Integer isNoEdu()
    {
        User cUser = (User) session.getAttribute("user");
        Integer ofId = cUser.getUser_office();
        switch (ofId)
        {
            case 143: case 144: case 145: case 146: case 147:
                return 100;
            case 148:
                return 101;
            default:
                return 111;
        }
    }
}
