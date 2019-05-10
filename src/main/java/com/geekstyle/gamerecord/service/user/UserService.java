package com.geekstyle.gamerecord.service.user;

import com.geekstyle.gamerecord.model.user.User;

public interface UserService {
	
	public void createUser(User user);
	
	public void insertUser(User user);
	
	public User getUserById(Integer id);
	
}
