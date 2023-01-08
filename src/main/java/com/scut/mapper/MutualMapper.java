package com.scut.mapper;

import com.scut.pojo.Member;
import com.scut.pojo.MutualData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MutualMapper {
    List<Member> selectMemberByDepartment(@Param("d_name") String d_name,@Param("ID")int ID);
    MutualData selectAllData(@Param("assess_ID")int assess_ID,@Param("be_assess_ID")int be_assess_ID,
                         @Param("year")int year,@Param("semester")int semester);
    void add(MutualData mutualData);
    void updateState(int ID);
}
