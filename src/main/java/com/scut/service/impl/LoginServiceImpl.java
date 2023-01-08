package com.scut.service.impl;

import com.scut.mapper.LoginMapper;
import com.scut.pojo.Administrator;
import com.scut.pojo.Member;
import com.scut.service.LoginService;
import com.scut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class LoginServiceImpl implements LoginService {

    @Override
    public Administrator selectAdministrator(String acc, String pwd) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        LoginMapper loginMapper = sqlSession.getMapper(LoginMapper.class);
        Administrator administrator = loginMapper.selectAdministrator(acc, pwd);
        sqlSession.close();
        return administrator;
    }

    @Override
    public Member selectMember(String acc, String pwd) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        LoginMapper loginMapper = sqlSession.getMapper(LoginMapper.class);
        Member member = loginMapper.selectMember(acc, pwd);
        sqlSession.close();
        return member;
    }

    @Override
    public Member selectMemberByID(int ID) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        LoginMapper loginMapper = sqlSession.getMapper(LoginMapper.class);
        Member member = loginMapper.selectMemberByID(ID);
        sqlSession.close();
        return member;
    }
}
