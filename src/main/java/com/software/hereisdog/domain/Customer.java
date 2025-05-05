package com.software.hereisdog.domain;
/**
 * 고객 정보를 담는 클래스 (User를 상속)
 */
public class Customer extends User {

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String username, String password, String name, String phoneNumber) {
		super(id, username, password, name, phoneNumber);
		// TODO Auto-generated constructor stub
	}

}
