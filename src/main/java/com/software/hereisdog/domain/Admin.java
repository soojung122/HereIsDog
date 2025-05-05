package com.software.hereisdog.domain;

/**
 * 관리자 정보를 담는 클래스 (User를 상속)
 */
public class Admin extends User {

    public Admin() {}

    public Admin(Long id, String username, String password, String name, String phoneNumber) {
        super(id, username, password, name, phoneNumber);
    }
}
