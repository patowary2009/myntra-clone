package com.github.patowary2009.myntra_clone.dto;

public class LoginRequest {
    private String usrName;
    private String password;

    public String getUsrName() {
        return usrName;
    }

    public void  setUserName(String userName) {
        this.usrName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
