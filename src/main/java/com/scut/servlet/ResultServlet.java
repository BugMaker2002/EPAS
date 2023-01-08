package com.scut.servlet;

import com.alibaba.fastjson.JSON;
import com.scut.pojo.Result;
import com.scut.service.ResultService;
import com.scut.service.impl.ResultServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ResultServlet/*")
public class ResultServlet extends BaseServlet{
    private ResultService resultService=new ResultServiceImpl();
    public void selectByCondition(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String dept_name = req.getParameter("dept_name");
        String post_name = req.getParameter("post_name");
        String year = req.getParameter("year");
        String semester = req.getParameter("semester");
        List<Result> results = resultService.selectByCondition("%"+dept_name+"%", "%"+post_name+"%", Integer.valueOf(year), Integer.valueOf(semester));
        //System.out.println(results);
        Map map=new HashMap<>();
        map.put("code",0);
        map.put("data",results);
        String s = JSON.toJSONString(map);
        //System.out.println(s);
        resp.getWriter().write(s);
    }
    public void selectAll(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        List<Result> results = resultService.selectAll();
        Map map=new HashMap<>();
        map.put("code",0);
        map.put("data",results);
        String s = JSON.toJSONString(map);
        //System.out.println(s);
        resp.getWriter().write(s);
    }
    public void chart1(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        List<Map<String, Object>> maps = resultService.computeSumByGrade();
        Map map=new HashMap<>();
        map.put("data",maps);
        String s = JSON.toJSONString(map);
        System.out.println(s);
        resp.getWriter().write(s);
    }
    public void chart2(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        Map map1=new HashMap<>();
        Map map2=new HashMap<>();
        Map map3=new HashMap<>();
        Map map4=new HashMap<>();
        map1.put("semester","第一季度");
        map1.put("Marketing Department",resultService.computeEBySemesterAndDepart("Marketing Department",1));
        map1.put("Business Department",resultService.computeEBySemesterAndDepart("Business Department",1));
        map1.put("Finance Department",resultService.computeEBySemesterAndDepart("Finance Department",1));
        map1.put("Trade Department",resultService.computeEBySemesterAndDepart("Trade Department",1));
        map1.put("Logistic Department",resultService.computeEBySemesterAndDepart("Logistic Department",1));
        map2.put("semester","第二季度");
        map2.put("Marketing Department",resultService.computeEBySemesterAndDepart("Marketing Department",2));
        map2.put("Business Department",resultService.computeEBySemesterAndDepart("Business Department",2));
        map2.put("Finance Department",resultService.computeEBySemesterAndDepart("Finance Department",2));
        map2.put("Trade Department",resultService.computeEBySemesterAndDepart("Trade Department",2));
        map2.put("Logistic Department",resultService.computeEBySemesterAndDepart("Logistic Department",2));
        map3.put("semester","第三季度");
        map3.put("Marketing Department",resultService.computeEBySemesterAndDepart("Marketing Department",3));
        map3.put("Business Department",resultService.computeEBySemesterAndDepart("Business Department",3));
        map3.put("Finance Department",resultService.computeEBySemesterAndDepart("Finance Department",3));
        map3.put("Trade Department",resultService.computeEBySemesterAndDepart("Trade Department",3));
        map3.put("Logistic Department",resultService.computeEBySemesterAndDepart("Logistic Department",3));
        map4.put("semester","第四季度");
        map4.put("Marketing Department",resultService.computeEBySemesterAndDepart("Marketing Department",4));
        map4.put("Business Department",resultService.computeEBySemesterAndDepart("Business Department",4));
        map4.put("Finance Department",resultService.computeEBySemesterAndDepart("Finance Department",4));
        map4.put("Trade Department",resultService.computeEBySemesterAndDepart("Trade Department",4));
        map4.put("Logistic Department",resultService.computeEBySemesterAndDepart("Logistic Department",4));
        List<Map<String, Object>> maps=new ArrayList<>();
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);
        maps.add(map4);
        Map map=new HashMap();
        map.put("data",maps);
        String s = JSON.toJSONString(map);
        System.out.println(s);
        resp.getWriter().write(s);
    }
    public void chart3(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        Map map=new HashMap<>();
        map.put("Marketing Department",resultService.computeSumByGradeAndDepart("Marketing Department"));
        map.put("Business Department",resultService.computeSumByGradeAndDepart("Business Department"));
        map.put("Finance Department",resultService.computeSumByGradeAndDepart("Finance Department"));
        map.put("Trade Department",resultService.computeSumByGradeAndDepart("Trade Department"));
        map.put("Logistic Department",resultService.computeSumByGradeAndDepart("Logistic Department"));
        String s = JSON.toJSONString(map);
        System.out.println(s);
        resp.getWriter().write(s);
    }
}
