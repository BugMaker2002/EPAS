package com.scut.service.impl;

import com.scut.mapper.AdministratorMapper;
import com.scut.mapper.LoginMapper;
import com.scut.pojo.Administrator;
import com.scut.pojo.Member;
import com.scut.service.AdministratorService;
import com.scut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AdministratorImpl implements AdministratorService {
    @Override
    public List<Member> selectAllMember() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
        List<Member> members = mapper.selectAllMember();
        sqlSession.close();
        return members;
    }

    @Override
    public List<Member> selectMemberByCondition(int ID, String name, String d_name, String position) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
        List<Member> members = mapper.selectMemberByCondition(ID, name, d_name, position);
        sqlSession.close();
        return members;
    }

    @Override
    public void update(Member member) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
        mapper.update(member);
        sqlSession.close();
    }

    @Override
    public void add(Member member) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
        mapper.add(member);
        sqlSession.close();
    }

    @Override
    public void delete(int ID) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        AdministratorMapper mapper = sqlSession.getMapper(AdministratorMapper.class);
        mapper.delete(ID);
        sqlSession.close();
    }
}
