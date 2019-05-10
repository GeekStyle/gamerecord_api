package com.geekstyle.gamerecord.service.user.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.gamerecord.dao.user.UserDao;
import com.geekstyle.gamerecord.model.user.Credential;
import com.geekstyle.gamerecord.model.user.User;
import com.geekstyle.gamerecord.service.user.CredentialService;
import com.geekstyle.gamerecord.service.user.UserService;
import com.geekstyle.gamerecord.util.StringUtil;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Autowired
	CredentialService credentialService;
	
	@Override
	public void createUser(User user) {
		Date createTime = new Date();
		user.setCreateTime(createTime);
		userDao.insertUser(user);
		
		Credential credential = new Credential();
		credential.setUserId(user.getId());
		credential.setPassword(user.getPassword());
		credential.setCreateTime(createTime);
		if(StringUtil.isNotEmpty(user.getNickname())) {
			credential.setUsername(user.getNickname());
			credential.setType(CredentialService.CREDENTIAL_TYPE_NICKNAME);
			credentialService.insertCredential(credential);
		}
		if(StringUtil.isNotEmpty(user.getEmail())) {
			credential.setUsername(user.getEmail());
			credential.setType(CredentialService.CREDENTIAL_TYPE_EMAIL);
			credentialService.insertCredential(credential);
		}
		if(StringUtil.isNotEmpty(user.getMobile())) {
			credential.setUsername(user.getMobile());
			credential.setType(CredentialService.CREDENTIAL_TYPE_MOBILE);
			credentialService.insertCredential(credential);
		}
		
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

}
