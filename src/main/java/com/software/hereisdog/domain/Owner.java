package com.software.hereisdog.domain;

/**
 * 업주 정보를 담는 클래스 (User를 상속)
 */
public class Owner extends User {

    public Owner() {
        super();
    }

    public Owner(Long id, String username, String password, String nickname, String email, String businessNumber) {
        super(id, username, password, nickname, email, businessNumber);
    }
}
