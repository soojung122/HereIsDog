package com.software.hereisdog.domain;

/**
 * 공통 사용자 정보를 담는 추상 클래스 (UI 기준)
 */
public abstract class User {
    private Long id;                // 시스템 부여 ID
    private String username;        // 사용자 ID
    private String password;        // 비밀번호
    private String nickname;        // 닉네임
    private String email;           // 이메일
    private String businessNumber;  // 사업자 번호 (owner만)

    public User() {}

    public User(Long id, String username, String password, String nickname, String email, String businessNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.businessNumber = businessNumber;
    }

    // Getter / Setter
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
}
