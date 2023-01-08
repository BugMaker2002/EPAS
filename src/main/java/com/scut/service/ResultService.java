package com.scut.service;

import com.scut.pojo.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResultService {
    List<Result> selectByCondition(String d_name,String position,int year,int semester);
    List<Result>selectAll();
    List<Map<String,Object>> computeSumByGrade();
    int computeEBySemesterAndDepart(String depart,int semester);
    List<Map<String,Object>> computeSumByGradeAndDepart(String depart);
}
