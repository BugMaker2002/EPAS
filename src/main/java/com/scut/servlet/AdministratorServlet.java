package com.scut.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scut.pojo.Member;
import com.scut.service.AdministratorService;
import com.scut.service.impl.AdministratorImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Administrator/*")
public class AdministratorServlet extends BaseServlet{
    private AdministratorService administratorService=new AdministratorImpl();
    public void selectAllMember(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        List<Member> members = administratorService.selectAllMember();
        Map map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",members.size());
        map.put("data",members);
        String s1 = JSON.toJSONString(map);
        //System.out.println(s1);
        resp.getWriter().write(s1);
    }
    public void selectMemberByCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        String id = req.getParameter("id");
        System.out.println(id);
        String name = req.getParameter("name");
        System.out.println(name);
        String d_name = req.getParameter("d_name");
        System.out.println(d_name);
        String position = req.getParameter("position");
        System.out.println(position);
        List<Member> members = administratorService.selectMemberByCondition(Integer.valueOf(id),"%"+name+"%",
                "%"+d_name+"%","%"+position+"%");
        Map map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",members.size());
        map.put("data",members);
        String s1 = JSON.toJSONString(map);
        System.out.println(s1);
        resp.getWriter().write(s1);
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        BufferedReader br=req.getReader();
        String s = br.readLine();
        System.out.println(s);
        Member member = JSON.parseObject(s, Member.class);
        administratorService.update(member);
        resp.setContentType("text/json;charset=utf-8");
        Map map=new HashMap<>();
        map.put("code",0);
        String s1 = JSON.toJSONString(map);
        resp.getWriter().write(s1);
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        BufferedReader br=req.getReader();
        String s = br.readLine();
        System.out.println(s);
        Member member = JSON.parseObject(s, Member.class);
        administratorService.add(member);
        resp.setContentType("text/json;charset=utf-8");
        Map map=new HashMap<>();
        map.put("code",0);
        String s1 = JSON.toJSONString(map);
        resp.getWriter().write(s1);
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        BufferedReader br=req.getReader();
        String s = br.readLine();
        JSONObject jsonObject = JSON.parseObject(s);
        administratorService.delete(Integer.valueOf(jsonObject.getString("ID")));
        Map map=new HashMap<>();
        map.put("data",0);
        String s1 = JSON.toJSONString(map);
        resp.getWriter().write(s1);
    }

}
