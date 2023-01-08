package com.scut.mapper;

import com.scut.pojo.Member;
import com.scut.pojo.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResultMapper {
    List<Result>selectByCondition(@Param("d_name")String d_name,@Param("position")String position,
                                  @Param("year")int year,@Param("semester")int semester);
    List<Result>selectAll();
}
