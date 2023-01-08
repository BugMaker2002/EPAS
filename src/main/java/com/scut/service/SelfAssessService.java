package com.scut.service;

import com.scut.pojo.SelfAssess;
import org.apache.ibatis.annotations.Param;

public interface SelfAssessService {
    SelfAssess select(int ID, int year, int semester);
    void submit(int ID,String description, int year,int semester);
}
