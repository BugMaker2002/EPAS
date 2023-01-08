package com.scut.pojo;

public class Member {
    private int ID;
    private String name;
    private String position;
    private String acc;
    private String pwd;
    private String d_name;
    private String state;

    @Override
    public String toString() {
        return "Member{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", acc='" + acc + '\'' +
                ", pwd='" + pwd + '\'' +
                ", d_name='" + d_name + '\'' +
                ", state='" + state + '\'' +
                ", register_time='" + register_time + '\'' +
                '}';
    }

    public Member(int ID, String name, String position, String acc, String pwd, String d_name, String state, String register_time) {
        this.ID = ID;
        this.name = name;
        this.position = position;
        this.acc = acc;
        this.pwd = pwd;
        this.d_name = d_name;
        this.state = state;
        this.register_time = register_time;
    }

    public Member() {
    }

    private String register_time;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }
}
