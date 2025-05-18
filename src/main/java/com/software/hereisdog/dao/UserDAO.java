package com.software.hereisdog.dao;
import com.software.hereisdog.domain.User;

public interface UserDAO {

	User findUserByUsername(String username);
}
