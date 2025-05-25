package com.software.hereisdog.controller;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;

/**
 * 회원가입 시 사용자로부터 입력받는 데이터
 */
public class SignupForm {

    //@NotEmpty(message = "아이디를 입력하세요.")
    private String username; //아이디

    //@NotEmpty(message = "비밀번호를 입력하세요.")
    //@Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
    private String password;
    
    private String ConfirmPassword;
    //@NotEmpty(message = "이메일을 입력하세요.")
    //@Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;
    
    //@NotEmpty(message = "닉네임을 입력하세요.")
    //@Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하로 입력하세요.")
    //private String nickname;               // 닉네임 필드 삭제(username 만으로 유저 구분)
    
    //@Pattern(regexp = "^[0-9]{10}$", message = "사업자 번호는 숫자 10자리여야 합니다.")
    private String businessNumber;         // 사업자 번호 (owner만)

    // 기본 생성자
    public SignupForm() {}

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

    public String getConfirmPassword() {
    	return ConfirmPassword;
    }
    
    public void setConfirmPassword(String ConfirmPassword) {
    	this.ConfirmPassword = ConfirmPassword;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
   
    public String getBusinessNumber() { return businessNumber; }
    public void setBusinessNumber(String businessNumber) { this.businessNumber = businessNumber; }
}
