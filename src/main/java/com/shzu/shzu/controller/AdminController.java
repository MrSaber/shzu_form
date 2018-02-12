package com.shzu.shzu.controller;

import com.shzu.shzu.mapper.FormMapper;
import com.shzu.shzu.mapper.OfficeMapper;
import com.shzu.shzu.model.GG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private OfficeMapper officeMapper;
    @Autowired
    private FormMapper formMapper;
    /**
     * 单独重置学院权限
     * @param xyId
     * @return
     */
    @RequestMapping("/resetQX.do")
    public String resetXQ(Integer xyId)
    {
        try {
            officeMapper.resetQX(xyId);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "设置成功";
    }

    /**
     * 获得公告
     * @return
     */
    @RequestMapping("/getGG.do")
    public GG getGG()
    {
        return formMapper.getGG();
    }

    /**
     * 添加公告
     * @param gg
     * @return
     */
    @RequestMapping("/addGG.do")
    public String addGG(GG gg)
    {
        try {
            formMapper.addGG(gg);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "添加成功";
    }

    /**
     * 开通所有人的一次权限
     * @return
     */
    @RequestMapping("/openAll.do")
    public String openAll()
    {
        try {
            officeMapper.resetAllQX();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "开通成功";
    }
}
