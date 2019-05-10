package com.geekstyle.gamerecord.dao.user;

import com.geekstyle.gamerecord.model.user.VerifyCode;

public interface VerifyCodeDao {

	public void insertVerifyCode(VerifyCode verifyCode);
	
	public VerifyCode getVerifyCode(VerifyCode verifyCode);
	
}
