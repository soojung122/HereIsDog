package com.software.hereisdog.domain;

/**
 * 공통 사용자 정보를 담는 추상 클래스 (UI 기준)
 */
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public abstract class User {
	
	@Id
    private Long id;                // 시스템 부여 ID
    private String username;        // 사용자 ID
    private String password;        // 비밀번호
    private String email;           // 이메일
    private String businessNumber;  // 사업자 번호 (owner만)
    private String userType;

    public User() {}

    public User(Long id, String username, String password, String email, String businessNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
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
    
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
