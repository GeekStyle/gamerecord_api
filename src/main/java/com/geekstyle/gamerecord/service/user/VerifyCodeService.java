package com.geekstyle.gamerecord.service.user;

import com.geekstyle.gamerecord.model.user.Credential;
import com.geekstyle.gamerecord.model.user.VerifyCode;

public interface VerifyCodeService {
	
	public String sendVerifyCode(Credential credential);
	
	public VerifyCode getVerifyCode(VerifyCode verifyCode);
}
