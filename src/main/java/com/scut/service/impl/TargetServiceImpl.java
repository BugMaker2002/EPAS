package com.scut.service.impl;

import com.scut.mapper.TargetMapper;
import com.scut.pojo.Target;
import com.scut.service.TargetService;
import com.scut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TargetServiceImpl implements TargetService {
    @Override
    public List<Target> selectByCondition(String d_name, String position, int year, int semester) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        List<Target> targets = mapper.selectByCondition(d_name, position, year, semester);
        sqlSession.close();
        return targets;
    }
    @Override
    public List<Target> selectAllTargets() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        List<Target> targets = mapper.selectAllTarget();
        sqlSession.close();
        return targets;
    }

    @Override
    public List<Target> selectTargetsByCondition(String d_name, String position, Integer year, Integer semester) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        List<Target> targets = mapper.selectTargetByCondition(d_name, position, year, semester);
        sqlSession.close();
        return targets;
    }

    @Override
    public void addTarget(Target target) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        mapper.add(target);
//        提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void modifyTarget(Target target) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        mapper.modify(target);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteTarget(Target target) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        mapper.delete(target);
        sqlSession.commit();
        sqlSession.close();
    }
}
