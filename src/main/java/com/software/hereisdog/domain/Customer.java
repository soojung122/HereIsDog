package com.software.hereisdog.domain;

/**
 * 고객 정보를 담는 클래스 (User를 상속)
 */
public class Customer extends User {

    public Customer() {
        super();
        setRole("customer");
    }

    public Customer(Long id, String username, String password, String email) {
        super(id, username, password, email, null);  // businessNumber는 null
    }
}
