package com.software.hereisdog.controller;

//import jakarta.validation.constraints.NotEmpty;

/**
 * 로그인 시 사용자로부터 입력받는 데이터
 */
public class LoginForm {

    //@NotEmpty(message = "아이디를 입력하세요.")
    private String username;

    //@NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;
    
    private String role; // "guest" or "owner"
    
    // 기본 생성자
    public LoginForm() {}

    // Getter & Setter
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
