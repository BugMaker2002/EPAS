package com.scut.servlet;

import com.alibaba.fastjson.JSON;
import com.scut.pojo.Member;
import com.scut.service.LoginService;
import com.scut.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/afterLoginServlet")
public class AfterLoginServlet extends HttpServlet {
    private LoginService loginService=new LoginServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        String id = req.getParameter("id");
        Map map=new HashMap();
        Member member = loginService.selectMemberByID(Integer.valueOf(id));
        //System.out.println(member);
        map.put("msg","成功");
        map.put("name",member.getName());
        map.put("department",member.getD_name());
        map.put("position",member.getPosition());
        String s = JSON.toJSONString(map);
        resp.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
