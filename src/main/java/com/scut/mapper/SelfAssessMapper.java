package com.scut.mapper;

import com.scut.pojo.SelfAssess;
import org.apache.ibatis.annotations.Param;

public interface SelfAssessMapper {
    SelfAssess select(@Param("ID") int ID, @Param("year") int year,@Param("semester")int semester);
    void submit(@Param("ID") int ID, @Param("description")String description,
                      @Param("year") int year,@Param("semester")int semester);
}
