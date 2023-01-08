package com.scut.service;

import com.scut.pojo.Target;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TargetService {
    List<Target> selectByCondition(String d_name, String position, int year, int semester);
    List<Target> selectAllTargets();
    List<Target> selectTargetsByCondition(String d_name, String position, Integer year, Integer semester);
    void addTarget(Target target);
    void modifyTarget(Target target);
    void deleteTarget(Target target);
}
