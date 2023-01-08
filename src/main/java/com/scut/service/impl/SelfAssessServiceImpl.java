package com.scut.service.impl;

import com.scut.mapper.SelfAssessMapper;
import com.scut.pojo.SelfAssess;
import com.scut.service.SelfAssessService;
import com.scut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SelfAssessServiceImpl implements SelfAssessService {
    @Override
    public SelfAssess select(int ID, int year, int semester) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SelfAssessMapper mapper = sqlSession.getMapper(SelfAssessMapper.class);
        SelfAssess select = mapper.select(ID, year, semester);
        sqlSession.close();
        return select;
    }

    @Override
    public void submit(int ID, String description, int year, int semester) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SelfAssessMapper mapper = sqlSession.getMapper(SelfAssessMapper.class);
        mapper.submit(ID, description, year, semester);
        sqlSession.close();
    }
}
