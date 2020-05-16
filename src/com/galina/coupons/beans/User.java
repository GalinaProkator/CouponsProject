package com.galina.coupons.beans;

import com.galina.coupons.enums.UserType;

public class User {

    private Long id;
    private String userName;
    private String password;
    private UserType type;
    private Long companyId;

    public User(String userName, String password, UserType type, Long companyId) {
        this.userName = userName;
        this.password = password;
        this.companyId = companyId;
        this.type = type;
    }

    public User(Long id, String userName, String password, UserType type, Long companyId) {
        this( userName,  password,  type, companyId );
        this.id = id;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public UserType getType() {
        return type;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", companyId=" + companyId +
                '}';
    }
}
