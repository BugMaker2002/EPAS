package com.scut.service.impl;

import com.scut.mapper.LoginMapper;
import com.scut.mapper.ResultMapper;
import com.scut.pojo.Member;
import com.scut.pojo.Result;
import com.scut.service.ResultService;
import com.scut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultServiceImpl implements ResultService {
    @Override
    public List<Result> selectByCondition(String d_name, String position, int year, int semester) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ResultMapper mapper = sqlSession.getMapper(ResultMapper.class);
        List<Result> results = mapper.selectByCondition(d_name, position, year, semester);
        sqlSession.close();
        return results;
    }

    @Override
    public List<Result> selectAll() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ResultMapper mapper = sqlSession.getMapper(ResultMapper.class);
        List<Result> results = mapper.selectAll();
        sqlSession.close();
        return results;
    }

    @Override
    public List<Map<String,Object>> computeSumByGrade() {
        int sumA=0;
        int sumB=0;
        int sumC=0;
        int sumD=0;
        int sumE=0;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ResultMapper mapper = sqlSession.getMapper(ResultMapper.class);
        List<Result> results = mapper.selectAll();
        for (Result result : results) {
            if ("A".equals(result.getGrade()))
            {
                sumA++;
            } else if ("B".equals(result.getGrade())) {
                sumB++;
            }
            else if ("C".equals(result.getGrade())) {
                sumC++;
            }
            else if ("D".equals(result.getGrade())) {
                sumD++;
            }
            else if ("E".equals(result.getGrade())) {
                sumE++;
            }
        }
        List<Map<String,Object>>list=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>();
        map1.put("type","A");
        map1.put("value",sumA);
        Map<String,Object> map2=new HashMap<>();
        map2.put("type","B");
        map2.put("value",sumB);
        Map<String,Object> map3=new HashMap<>();
        map3.put("type","C");
        map3.put("value",sumC);
        Map<String,Object> map4=new HashMap<>();
        map4.put("type","D");
        map4.put("value",sumD);
        Map<String,Object> map5=new HashMap<>();
        map5.put("type","E");
        map5.put("value",sumE);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        sqlSession.close();
        return list;
    }

    @Override
    public int computeEBySemesterAndDepart(String depart, int semester) {
        int sum=0;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ResultMapper mapper = sqlSession.getMapper(ResultMapper.class);
        LoginMapper mapper1 = sqlSession.getMapper(LoginMapper.class);
        List<Result> results = mapper.selectAll();
        for (Result result : results) {
            int id = result.getID();
            Member member = mapper1.selectMemberByID(id);
            if ((member.getD_name().equals(depart))&&(result.getSemester()==semester)&&("E".equals(result.getGrade())))
            {
                sum++;
            }
        }
        return sum;
    }

    @Override
    public List<Map<String, Object>> computeSumByGradeAndDepart(String depart) {
        int sumA=0;
        int sumB=0;
        int sumC=0;
        int sumD=0;
        int sumE=0;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ResultMapper mapper = sqlSession.getMapper(ResultMapper.class);
        LoginMapper mapper1 = sqlSession.getMapper(LoginMapper.class);
        List<Result> results = mapper.selectAll();
        for (Result result : results) {
            int id = result.getID();
            Member member = mapper1.selectMemberByID(id);
            if (member.getD_name().equals(depart)) {
                if ("A".equals(result.getGrade()))
                {
                    sumA++;
                } else if ("B".equals(result.getGrade())) {
                    sumB++;
                }
                else if ("C".equals(result.getGrade())) {
                    sumC++;
                }
                else if ("D".equals(result.getGrade())) {
                    sumD++;
                }
                else if ("E".equals(result.getGrade())) {
                    sumE++;
                }
            }
        }
        List<Map<String,Object>>list=new ArrayList<>();
        Map<String,Object> map1=new HashMap<>();
        map1.put("type","A");
        map1.put("value",sumA);
        Map<String,Object> map2=new HashMap<>();
        map2.put("type","B");
        map2.put("value",sumB);
        Map<String,Object> map3=new HashMap<>();
        map3.put("type","C");
        map3.put("value",sumC);
        Map<String,Object> map4=new HashMap<>();
        map4.put("type","D");
        map4.put("value",sumD);
        Map<String,Object> map5=new HashMap<>();
        map5.put("type","E");
        map5.put("value",sumE);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        sqlSession.close();
        return list;
    }
}
