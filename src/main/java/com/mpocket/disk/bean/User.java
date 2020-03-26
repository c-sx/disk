package com.mpocket.disk.bean;

import java.math.BigDecimal;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Boolean enable;

    private Boolean locked;

    private String email;

    private String phoneNumber;

    private Integer ipAddress;

    private String nameCheck;

    private BigDecimal money;

    private String userIcon;

    public User(String name, String password, Boolean enable, Boolean locked, String email) {
        this.name = name;
        this.password = password;
        this.enable = enable;
        this.locked = locked;
        this.email = email;
    }

    public User(String name, String password, Boolean enable, Boolean locked, String email, String phoneNumber, String userIcon) {
        this.name = name;
        this.password = password;
        this.enable = enable;
        this.locked = locked;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userIcon = userIcon;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(Integer ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getNameCheck() {
        return nameCheck;
    }

    public void setNameCheck(String nameCheck) {
        this.nameCheck = nameCheck == null ? null : nameCheck.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }
}