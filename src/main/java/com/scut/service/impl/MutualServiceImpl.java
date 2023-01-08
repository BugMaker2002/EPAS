package com.scut.service.impl;

import com.scut.mapper.LoginMapper;
import com.scut.mapper.MutualMapper;
import com.scut.pojo.Member;
import com.scut.pojo.MutualData;
import com.scut.service.MutualService;
import com.scut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MutualServiceImpl implements MutualService {
    @Override
    public MutualData selectAllData(int assess_ID, int be_assess_ID, int year, int semester) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        MutualMapper mapper = sqlSession.getMapper(MutualMapper.class);
        MutualData mutualData = mapper.selectAllData(assess_ID, be_assess_ID, year, semester);
        sqlSession.close();
        return mutualData;
    }

    @Override
    public List<Member> getAllPeopleData(int ID) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
        Member member = mapper.selectMemberByID(ID);
        MutualMapper mapper1 = sqlSession.getMapper(MutualMapper.class);
        List<Member> members = mapper1.selectMemberByDepartment(member.getD_name(), ID);
        sqlSession.close();
        return members;
    }

    @Override
    public void add(MutualData mutualData) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        MutualMapper mapper = sqlSession.getMapper(MutualMapper.class);
        mapper.add(mutualData);
        sqlSession.close();
    }

    @Override
    public void updateState(int ID) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        MutualMapper mapper = sqlSession.getMapper(MutualMapper.class);
        mapper.updateState(ID);
        sqlSession.close();
    }

}
