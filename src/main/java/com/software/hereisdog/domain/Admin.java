package com.software.hereisdog.domain;

/**
 * 관리자 정보를 담는 클래스 (User를 상속)
 * 시스템에서 내부적으로 관리되며, UI를 통해 직접 가입되지 않음
 */
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(Long id, String username, String password, String nickname, String email) {
        super(id, username, password, nickname, email, null); // businessNumber는 사용하지 않음
    }
}
