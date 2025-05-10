package com.software.hereisdog.domain;

/**
 * 공통 사용자 정보를 담는 추상 클래스*/
public abstract class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String phoneNumber;

    public User() {}

    public User(Long id, String username, String password, String name, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
