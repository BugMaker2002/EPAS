package com.scut.mapper;

import com.scut.pojo.Administrator;
import com.scut.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginMapper {
    Administrator selectAdministrator(@Param("acc") String acc, @Param("pwd") String pwd);
    Member selectMember(@Param("acc") String acc, @Param("pwd") String pwd);
    Member selectMemberByID(int ID);
}