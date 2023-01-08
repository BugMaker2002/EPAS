package com.scut.servlet;

import com.alibaba.fastjson.JSON;
import com.scut.pojo.Member;
import com.scut.pojo.MutualData;
import com.scut.service.LoginService;
import com.scut.service.MutualService;
import com.scut.service.impl.LoginServiceImpl;
import com.scut.service.impl.MutualServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/MutualServlet/*")
public class MutualServlet extends BaseServlet{
    private MutualService mutualService=new MutualServiceImpl();
    public void getAllPeopleData(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        String id = req.getParameter("id");
        //System.out.println(id);
        List<Member> allPeopleData = mutualService.getAllPeopleData(Integer.valueOf(id));
        Map map=new HashMap<>();
        map.put("msg","成功");
        map.put("data",allPeopleData);
        String s = JSON.toJSONString(map);
        //System.out.println(s);
        resp.getWriter().write(s);
    }
    public void receiveData(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        MutualData mutualData=new MutualData();
        mutualData.setAssess_ID(Integer.valueOf(req.getParameter("assess_id")));
        mutualData.setBe_assess_ID(Integer.valueOf(req.getParameter("be_assessed_id")));
        mutualData.setRelationship(req.getParameter("relation"));
        mutualData.setResult(req.getParameter("result"));
        mutualData.setSemester(Integer.valueOf(req.getParameter("quarter")));
        mutualData.setYear(Integer.valueOf(req.getParameter("year")));
        mutualService.add(mutualData);
        Map map=new HashMap<>();
        map.put("msg","成功");
        String s = JSON.toJSONString(map);
        resp.getWriter().write(s);
        mutualService.updateState(Integer.valueOf(req.getParameter("be_assessed_id")));
    }
    public void getMutualData(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        MutualData mutualData = mutualService.selectAllData(Integer.valueOf(req.getParameter("assess_id")),
                Integer.valueOf(req.getParameter("be_assessed_id")),
                Integer.valueOf(req.getParameter("year")),
                Integer.valueOf(req.getParameter("quarter")));
        Map map=new HashMap<>();
        map.put("msg","成功");
        String result = mutualData.getResult();
        List<Integer>list=new ArrayList<>();
        for (int i = 0; i < result.length(); i++) {
            if ((result.charAt(i)>='1')&&((result.charAt(i)<='5')))
            {
                list.add(Integer.valueOf(result.charAt(i))-48);
            }
        }
        map.put("data",list);
        String s = JSON.toJSONString(map);
        System.out.println(s);
        resp.getWriter().write(s);
    }
}
