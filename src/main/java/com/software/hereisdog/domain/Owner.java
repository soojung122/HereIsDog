package com.software.hereisdog.domain;

/**
 * 업주 정보를 담는 클래스 (User를 상속)
 */
public class Owner extends User {

    public Owner() {
        super();
        setRole("owner");
    }

    public Owner(Long id, String username, String password, String email, String businessNumber) {
        super(id, username, password, email, businessNumber);
    }
    public String getBusinessNumber() {
        return super.getBusinessNumber(); // 또는 getRole()처럼 바로 상위 클래스 호출
    }
}
