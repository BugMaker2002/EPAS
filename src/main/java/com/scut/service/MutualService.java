package com.scut.service;

import com.scut.pojo.Member;
import com.scut.pojo.MutualData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MutualService {
    MutualData selectAllData(int assess_ID,int be_assess_ID, int year,int semester);
    List<Member> getAllPeopleData(int ID);
    void add(MutualData mutualData);
    void updateState(int ID);
}
