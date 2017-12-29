package com.niit.Dao;

import com.niit.model.User;

public interface UserDao {
	void registerUser(User user);
	boolean isEmailValid(String email);
	boolean isUsernameValid(String username);
	User login(User user);
	void update(User user);
	User getUserByUsername(String username);

}
