package com.scut.mapper;

import com.scut.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorMapper {
    List<Member>selectAllMember();
    List<Member>selectMemberByCondition(
            @Param("ID") int ID, @Param("name") String name,
            @Param("d_name")String d_name,@Param("position")String position);
    void add(Member member);
    void update(Member member);
    void delete(int ID);
}
