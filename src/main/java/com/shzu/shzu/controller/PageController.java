package com.shzu.shzu.controller;

import com.shzu.shzu.filter.RoleCheck;
import com.shzu.shzu.mapper.CePinMapper;
import com.shzu.shzu.mapper.ExpertMapper;
import com.shzu.shzu.mapper.FormMapper;
import com.shzu.shzu.mapper.UserMapper;
import com.shzu.shzu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static javax.swing.text.html.CSS.getAttribute;

/***
 * 快捷键： ALT + 上下箭头 方法之间快速移动！！！
 */
@Controller
public class PageController {
    @Autowired
    private ExpertMapper expertMapper;
    @Autowired
    private CePinMapper cePinMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private FormMapper formMapper;

    @RoleCheck(level = 2)
    @RequestMapping("expert.html")
    public String expert_html(Model model)
    {
        User user= (User) session.getAttribute("user");
        model.addAttribute("academy",expertMapper.getAcademys(user.getUser_id()));
        model.addAttribute("gg",formMapper.getGG());
        return "experts";
    }

    @RoleCheck(level = 1)
    @RequestMapping("academy.html")
    public String academy_html(Model model)
    {
        model.addAttribute("cps",cePinMapper.getAll());
        model.addAttribute("gg",formMapper.getGG());
        return "academy";
    }

    @RoleCheck(level = 3)
    @RequestMapping("department.html")
    public String depart_html(Model model)
    {
        User user= (User) session.getAttribute("user");
        user.setUser_other(getBMFormPath(user));
        model.addAttribute("cps",cePinMapper.getAll());
        model.addAttribute("gg",formMapper.getGG());
        System.out.println(getBMFormPath(user));
        return "department";
    }

    /**
     * 根据用户权限获得表单
     * @param user
     * @return
     */
    public String getBMFormPath(User user)
    {
        Integer t= user.getUser_office();
        switch (t)
        {
            case 152:return "form_part_2_1.html";   //保密办
            case 153:return "form_part_2_2.html";   //保卫办
            case 154:return "form_part_3_1.html";   //学工部
            case 155:return "form_part_3_2.html";   //研工部
            case 156:return "form_part_45.html";   //组织部
            case 157:return "form_part_6.html";    //纪委
            case 158:return "form_part_7_1.html"; //公会
            case 159:return "form_part_7_2.html"; //团委
            case 160:return "form_part_8.html"; //宣传部
            case 161:return "form_part_9.html"; //统战部
            case 162:return "form_part_10.html"; //离退休工作处
            case 163:return "form_part_1.html"; //党办
        }
        return null;
    }

    /**
     * 系统管理员的专家管理界面
     * @return
     */
    @RoleCheck(level = 4)
    @RequestMapping("admin/exManage.html")
    public String admin_exManage_html()
    {
        return "admin/exManage";
    }
    /**
     * 系统管理员的用户管理界面
     * @return
     */
    @RoleCheck(level = 4)
    @RequestMapping("admin/uManage.html")
    public String admin_uManage_html()
    {
        return "admin/uManage";
    }
    /**
     * 系统管理员的部门管理界面
     * @return
     */
    @RoleCheck(level = 4)
    @RequestMapping("admin/cpManage.html")
    public String admin_cpManage_html()
    {
        return "admin/cpManage";
    }
    /**
     * 系统管理员的部门管理界面
     * @return
     */
    @RoleCheck(level = 4)
    @RequestMapping("admin/dpManage.html")
    public String admin_dpManage_html()
    {
        return "admin/dpManage";
    }

    /**
     * 系统管理员的发布界面
     * @return
     */
    @RoleCheck(level = 4)
    @RequestMapping("admin/privilegeManage.html")
    public String admin_privilegeManage_html()
    {
        return "admin/privilegeManage";
    }
    /**
     * 系统管理员的测评管理界面
     * @return
     */

    /**
     * 系统管理员的管理界面
     * @return
     */
    @RoleCheck(level = 4)
    @RequestMapping("admin.html")
    public String admin_Manage_html(Model model)
    {
        model.addAttribute("cps",cePinMapper.getAll());
        return "admin";
    }

    /**
     * 系统管理员公告界面
     */
    @RoleCheck(level = 4)
    @RequestMapping("admin/ggManage.html")
    public String admin_ggManage_html()
    {
        return "admin/ggManage";
    }

    /**
     * 系统管理员公告界面
     */
    @RequestMapping("admin/resultManage.html")
    public String admin_ResultManage_html()
    {
        return "admin/resultManage";
    }
}
