package com.scut.service;

import com.scut.pojo.Administrator;
import com.scut.pojo.Member;

import java.util.List;

public interface LoginService {
    Administrator selectAdministrator(String acc,String pwd);
    Member selectMember(String acc,String pwd);
    Member selectMemberByID(int ID);
}
