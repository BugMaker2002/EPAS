package com.scut.servlet;

import com.alibaba.fastjson.JSON;
import com.scut.pojo.SelfAssess;
import com.scut.service.SelfAssessService;
import com.scut.service.impl.SelfAssessServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SelfAssess/*")
public class SelfAssessServlet extends BaseServlet{
    private SelfAssessService service=new SelfAssessServiceImpl();
    public void select(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        String id = req.getParameter("id");
        String year = req.getParameter("year");
        String semester = req.getParameter("quarter");
        //System.out.println(id+" "+year+" "+semester);
        SelfAssess select = service.select(Integer.valueOf(id), Integer.valueOf(year), Integer.valueOf(semester));
        Map map=new HashMap<>();
        map.put("msg","成功");
        map.put("data",select.getDescription());
        String s = JSON.toJSONString(map);
        resp.getWriter().write(s);
    }
    public void submit(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String description = req.getParameter("content");
        String year = req.getParameter("year");
        String semester = req.getParameter("quarter");
        service.submit(Integer.valueOf(id),description,Integer.valueOf(year),Integer.valueOf(semester));
        Map map=new HashMap<>();
        map.put("msg","成功");
        String s = JSON.toJSONString(map);
        resp.getWriter().write(s);
    }
}
