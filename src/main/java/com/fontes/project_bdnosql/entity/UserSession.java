package com.fontes.project_bdnosql.entity;


import java.time.Instant;

public class UserSession {
    private String username;
    private Instant loginTime;
    private String device;

    public UserSession(String username, Instant loginTime, String device) {
        this.username = username;
        this.loginTime = loginTime;
        this.device = device;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
