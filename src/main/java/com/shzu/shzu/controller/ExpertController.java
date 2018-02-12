package com.shzu.shzu.controller;

import com.shzu.shzu.mapper.CePinMapper;
import com.shzu.shzu.mapper.ExpertMapper;
import com.shzu.shzu.mapper.FormMapper;
import com.shzu.shzu.mapper.UserMapper;
import com.shzu.shzu.model.Form;
import com.shzu.shzu.model.KeyValue;
import com.shzu.shzu.model.Office;
import com.shzu.shzu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ex")
public class ExpertController {
    @Autowired
    private ExpertMapper expertMapper;
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private CePinMapper cePinMapper;
    @Autowired
    private HttpSession httpSession;


    @RequestMapping("/getAcademys.do")
    public List<Office> getAcademys(Integer exId) {
        return expertMapper.getAcademys(exId);
    }

    @RequestMapping("/saveZJ.do")
    public String save(Form form, HttpSession session) {
        User cuUser = (User) session.getAttribute("user");
        form.setForm_expert_id(cuUser.getUser_id());
        formMapper.save(form);
        return "成功";
    }

    @RequestMapping("/add.do")
    public String add(Form form, HttpSession session) {
        User cuUser = (User) session.getAttribute("user");
        if(new Date().after(cePinMapper.getDeadDate(form.getForm_cp_id())))
            return "已过评测时间！提交失效！";
        form.setForm_expert_id(cuUser.getUser_id());
        System.out.println("专家Id为" + form.getForm_expert_id());
        formMapper.add(form);
        return "成功";
    }

    @RequestMapping("/get.do")
    public String get(HttpSession session) {
        User cuUser = (User) session.getAttribute("user");
        System.out.println(cuUser);
        return cuUser.toString();
    }

    /**
     * 添加学院
     * @param xyId
     * @return
     */
    @RequestMapping("/addXY.do")
    public String addXY(Integer xyId,Integer exId)
    {
        try{expertMapper.addXY(exId,xyId);}
        catch (Exception e)
        {   e.printStackTrace();
            return e.getMessage();
        }
        return "OK";
    }

    /**
     * 删除学院
     * @param xyId
     * @return
     */
    @RequestMapping("/delXY.do")
    public String delXY(Integer xyId)
    {
        User user= (User) httpSession.getAttribute("user");
        try{expertMapper.delXY(user.getUser_id(),xyId);}
        catch (Exception e)
        {return e.getMessage();}
        return "OK";
    }

    /**
     * 获得学院
     * @return
     */
    @RequestMapping("/getXY.do")
    public List<KeyValue> getXY(Integer exId)
    {
        List<KeyValue> keyValues = new ArrayList<>();
        try{
            List<Office> ofs=expertMapper.getAcademys(exId);
            Iterator<Office> it = ofs.iterator();
            while (it.hasNext())
            {
                Office of = it.next();
                KeyValue akey = new KeyValue();
                akey.setText(of.getOf_name());
                akey.setValue(String.valueOf(of.getOf_id()));
                keyValues.add(akey);
            }
        }
        catch (Exception e)
        {return null;}
        return keyValues;
    }

    @RequestMapping("/addU.do")
    public String addU(User user)
    {
        try{expertMapper.addU(user);}
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }

    @RequestMapping("/delU.do")
    public String delU(Integer user_id)
    {
        try{expertMapper.deleteU(user_id);}
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "删除成功";
    }

    @RequestMapping("/updateU.do")
    public String updateU(User user,Integer user_id)
    {
        user.setUser_id(user_id);
        try{expertMapper.updateU(user);}
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        return "更新成功";
    }

    @RequestMapping("/getAll.do")
    public List<User> getAll()
    {
        List<User> users =expertMapper.getAll();
        Iterator<User> it = users.iterator();
        while (it.hasNext())
        {
            User temp = it.next();

            List<Office> ofs = expertMapper.getAcademys(temp.getUser_id());
            System.out.println("专家号为"+temp.getUser_id());
            StringBuilder sb = new StringBuilder();
            Iterator<Office> it_of = ofs.iterator();
            while (it_of.hasNext())
            {
                sb.append(it_of.next().getOf_name()+"、");
            }
            System.out.println(sb.toString());
            temp.setUser_academy(sb.toString());
        }
        return users;
    }
}
