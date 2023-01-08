package com.scut.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scut.pojo.Administrator;
import com.scut.pojo.Member;
import com.scut.service.LoginService;
import com.scut.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private LoginService loginService=new LoginServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        //System.out.println(s);
//        System.out.println(req.getParameter("type"));
//        System.out.println(req.getParameter("account"));
//        System.out.println(req.getParameter("password"));
        JSONObject jsonObject= JSON.parseObject(s);
        String type=jsonObject.getString("type");
        String acc=jsonObject.getString("account");
        String pwd=jsonObject.getString("password");
        //System.out.println(type+acc+pwd);
        if ("admin login".equals(type))
        {
            //System.out.println(type);
            Administrator administrator = loginService.selectAdministrator(acc,pwd);
            if (administrator!=null)
            {
                //System.out.println("查到了该用户");
                Map map=new HashMap();
                map.put("data","成功");
                map.put("msg","admin login");
                String s1 = JSON.toJSONString(map);
                //System.out.println(s1);
                resp.getWriter().write(s1);
            }
            else
            {
                System.out.println("没查到该用户");
                resp.getWriter().write("失败");
            }
        }
        else if("staff login".equals(type))
        {
            //System.out.println(type);
            Member member = loginService.selectMember(acc, pwd);
            if (member!=null)
            {
                //System.out.println("查到了该用户");
                Map map=new HashMap();
                map.put("data","成功");
                map.put("msg","staff login");
                map.put("id",member.getID());
                String s1 = JSON.toJSONString(map);
                //System.out.println(s1);
                resp.getWriter().write(s1);
            }
            else
            {
                System.out.println("无该用户");
            }
        }
        else {
            System.out.println("不行");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
