package com.scut.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scut.pojo.Member;
import com.scut.pojo.Target;
import com.scut.service.TargetService;
import com.scut.service.impl.TargetServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@WebServlet("/TargetServlet/*")
public class TargetServlet extends BaseServlet{
    private TargetService targetService=new TargetServiceImpl();
    public void selectByCondition(HttpServletRequest req, HttpServletResponse resp)throws IOException
    {
        resp.setContentType("text/json;charset=utf-8");
        String dept = req.getParameter("dept");
        String pos = req.getParameter("pos");
        String year = req.getParameter("year");
        String quarter = req.getParameter("quarter");
        //System.out.println(dept+" "+pos+" "+year+" "+quarter);
        List<Target> targets = targetService.selectByCondition(dept, pos, Integer.valueOf(year), Integer.valueOf(quarter));
        List<String>data=new ArrayList<>();
        for (Target target : targets) {
            data.add(target.getDescription());
        }
        Map map=new HashMap<>();
        map.put("msg","成功");
        map.put("data",data);
        String s = JSON.toJSONString(map);
        //System.out.println(s);
        resp.getWriter().write(s);
    }
    public void selectAllTargets(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=utf-8");
        List<Target> targets = targetService.selectAllTargets();
        Map map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", targets.size());
        map.put("data", targets);

        String s1 = JSON.toJSONString(map);
        System.out.println("selectAll被调用："+s1);
        resp.getWriter().write(s1);
    }

    public void selectTargetsByCondition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=utf-8");
        String dept_name = req.getParameter("dept_name");
        String post_name = req.getParameter("post_name");
        Integer year = null;
        Integer semester = null;
        if (req.getParameter("year") != null && !req.getParameter("year").equals("")) {
            year = Integer.parseInt(req.getParameter("year"));
        }
        if(req.getParameter("semester") != null && !req.getParameter("semester").equals("")){
            semester = Integer.parseInt(req.getParameter("semester"));
        }
        List<Target> targets = targetService.selectTargetsByCondition(dept_name, post_name, year, semester);
        Map map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", targets.size());
        map.put("data", targets);
        String s1 = JSON.toJSONString(map);
        System.out.println("selectByCondition被调用："+s1);
        resp.getWriter().write(s1);
    }

    public void addTarget(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //      解决post提交表单出现中文乱码的问题
        req.setCharacterEncoding("utf-8");

        resp.setContentType("text/json;charset=utf-8");
        BufferedReader br=req.getReader();
        String s = br.readLine();
        System.out.println(s);
        Target target = JSON.parseObject(s, Target.class);
        target.setT_index(1);
        System.out.println(target);
        targetService.addTarget(target);
        Map map = new HashMap<>();
        map.put("code", 0);
        String s1 = JSON.toJSONString(map);
        resp.getWriter().write(s1);
    }

    public void modifyTarget(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //      解决post提交表单出现中文乱码的问题
        req.setCharacterEncoding("utf-8");

        resp.setContentType("text/json;charset=utf-8");
        BufferedReader br=req.getReader();
        String s = br.readLine();
        System.out.println(s);
        Target target = JSON.parseObject(s, Target.class);
        System.out.println(target);
        targetService.modifyTarget(target);
        Map map = new HashMap<>();
        map.put("code", 0);
        String s1 = JSON.toJSONString(map);
        resp.getWriter().write(s1);
    }

    public void deleteTarget(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //      解决post提交表单出现中文乱码的问题
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        BufferedReader br=req.getReader();
        String s = br.readLine();
        Target target = JSON.parseObject(s, Target.class);
        System.out.println(target);
        targetService.deleteTarget(target);
        Map map = new HashMap<>();
        map.put("code", 0);
        String s1 = JSON.toJSONString(map);
        resp.getWriter().write(s1);
    }
}
