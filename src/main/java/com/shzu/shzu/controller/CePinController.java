package com.shzu.shzu.controller;

import com.shzu.shzu.mapper.CePinMapper;
import com.shzu.shzu.mapper.FormMapper;
import com.shzu.shzu.model.CePin;
import com.shzu.shzu.model.Form;
import com.shzu.shzu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("cp")
public class CePinController {
    @Autowired
    private CePinMapper cePinMapper;
    @Autowired
    private FormMapper formMapper;

    @RequestMapping("/add.do")
    public String add(CePin cePin)
    {
        cePinMapper.add(cePin);
        return cePin.toString();
    }
    @RequestMapping("/getAll.do")
    public List<CePin> getAll()
    {

        return cePinMapper.getAll();
    }
    /**
     * 返回三种模式的测评信息
     * @param xyId
     * @param cpId
     * @param model
     * @return
     */
    @RequestMapping("/cpget.do")
    public Form cpGet(Integer xyId,Integer cpId,Integer model)
    {
        if(model==1)
            return  formMapper.getByTJ(cpId,xyId);
        if(model==3)
            return  formMapper.getcpBM(cpId,xyId);
        if(model==2)
            return  formMapper.getcpZJ(cpId,xyId);
        return null;
    }

    @RequestMapping("/cpgetBM.do")
    public Form cpGetBM(Integer xyId,Integer cpId)
    {
        return formMapper.getcpBM(cpId,xyId);
    }

    @RequestMapping("/getResult.do")
    public List<Result> getResult(Integer cpId)
    {
        Integer[] ofId = {1,2,3,4,5,126,127,128,129,130,131,132,133,134,135,136,138,139,140,141,142,143,144,145,146,147,148,149,150,151};
        String[] ofName ={"信息学院","医学院","农学院","法学院","师范学院","政法学院","马克思主义学院","体育学院","文学艺术学院","外国语学院","理学院","生命科学学院","化学化工学院","机电学院","水建学院","食品学院",
        "动物科技学院","药学院","经管学院","商学院","继续教育学院","离退休工作处","后勤管理处","图书馆(党总支)","校医院(党总支)","实验场", "一附院", "护校", "竞技体校(党总支)"," 机关党工委"};
        List<Result> results = new ArrayList<>();
       for(int i=0;i<ofId.length;i++)
       {
           Result result = new Result();
           result.setAcademy_name(ofName[i]);

           Double form_self =0.0;
           Double form_expert =0.0;
           Double form_depart =0.0;

           Form form_s = formMapper.getByTJ(cpId,ofId[i]);
           Form form_e = formMapper.getcpZJ(cpId,ofId[i]);
           Form form_d = formMapper.getcpBM(cpId,ofId[i]);
           if(form_s != null)
                form_self = form_s.getSum();
           if(form_e !=null)
               form_expert = form_e.getSum();
           if(form_d !=null)
               form_depart = form_d.getSum();

           result.setScore_self(form_self);
           result.setScore_expert(form_expert);
           result.setScore_depart(form_depart);

           results.add(result);
       }
       return results;
    }

    @RequestMapping("/excel.do")
    public void excel(HttpServletResponse response, String txtContent)
    {
        System.out.println(txtContent);
        response.setHeader("charset","utf-8");
        response.setContentType("Application/ms-excel");
        response.addHeader("content-disposition","attachment;filename=data.xls");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write("<html>\\n<head>\\n");
            response.getWriter().write("<style type=\"text/css\">\n.pb{font-size:13px;border-collapse:collapse;} "+
                    "\n.pb th{font-weight:bold;text-align:center;border:0.5pt solid windowtext;padding:2px;} " +
                    "\n.pb td{border:0.5pt solid windowtext;padding:2px;}\n</style>\n</head>\n");

            response.getWriter().write("<body>\n" +txtContent + "\n</body>\n</html>");
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
