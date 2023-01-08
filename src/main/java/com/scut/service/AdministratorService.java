package com.scut.service;

import com.scut.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorService {
    List<Member> selectAllMember();
    List<Member>selectMemberByCondition(int ID, String name, String d_name, String position);
    void update(Member member);
    void add(Member member);
    void delete(int ID);
}
