package com.taihe.databasedemo.dto;

public class User {
    private Long loginId;
    private String password;

    public User(Long loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
