package com.scut.pojo;

public class Administrator {
    private String acc;

    public void setAcc(String acc) {
        this.acc = acc;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "acc='" + acc + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public Administrator() {
    }

    public Administrator(String acc, String pwd) {
        this.acc = acc;
        this.pwd = pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAcc() {
        return acc;
    }

    public String getPwd() {
        return pwd;
    }

    private String pwd;
}
