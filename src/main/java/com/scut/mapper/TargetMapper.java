package com.scut.mapper;

import com.scut.pojo.Target;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TargetMapper {
    List<Target> selectByCondition(@Param("d_name")String d_name,@Param("position")String position,
                                   @Param("year")int year,@Param("semester")int semester);

    @Select("select * from t_target")
    List<Target> selectAllTarget();
    List<Target> selectTargetByCondition(@Param("d_name") String d_name, @Param("position") String position,
                                         @Param("year") Integer year, @Param("semester") Integer semester);
    void add(Target target);
    void delete(Target target);
    void modify(Target target);
}
