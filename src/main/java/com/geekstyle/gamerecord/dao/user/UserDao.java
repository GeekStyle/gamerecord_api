package com.geekstyle.gamerecord.dao.user;

import com.geekstyle.gamerecord.model.user.User;

public interface UserDao {
	
	public void insertUser(User user);
	
	public User getUserById(Integer id);
	
}