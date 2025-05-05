package com.software.hereisdog.domain;

/**
 * 업주 정보를 담는 클래스 (User를 상속)
 */
public class Owner extends User {

    private String businessNumber;

    public Owner() {}

    public Owner(Long id, String username, String password, String name, String phoneNumber, String businessNumber) {
        super(id, username, password, name, phoneNumber);
        this.businessNumber = businessNumber;
    }

    // getter/setter
    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
}