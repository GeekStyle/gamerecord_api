package com.geekstyle.gamerecord.service.user.impl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstyle.gamerecord.dao.user.VerifyCodeDao;
import com.geekstyle.gamerecord.model.user.Credential;
import com.geekstyle.gamerecord.model.user.VerifyCode;
import com.geekstyle.gamerecord.service.common.ResponseService;
import com.geekstyle.gamerecord.service.user.CredentialService;
import com.geekstyle.gamerecord.service.user.VerifyCodeService;
import com.geekstyle.gamerecord.util.MailUtil;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	
	@Autowired
	CredentialService credentialService;
	@Autowired
	VerifyCodeDao verifyCodeDao;

	@Override
	public String sendVerifyCode(Credential credential) {
		if(credentialService.isExist(credential)) {
			String code = generateVerifyCode();
			VerifyCode verifyCode = new VerifyCode();
			verifyCode.setCode(code);
			verifyCode.setUsername(credential.getUsername());
			verifyCode.setCreateTime(new Date());
			verifyCodeDao.insertVerifyCode(verifyCode);
			MailUtil.sendRestPasswordEmail(code, credential.getUsername());
			return ResponseService.SUCCESS;
		}else {
			return ResponseService.NOT_EXIST;
		}
	}
	
	private String generateVerifyCode() {
		Random random = new Random();
		String code = "";
		for(int i=0;i<4;i++) {
			String digit = Integer.toString(random.nextInt(10));
			code += digit;
		}
		return code;
	}
	
	@Override
	public VerifyCode getVerifyCode(VerifyCode verifyCode) {
		return verifyCodeDao.getVerifyCode(verifyCode);
	}
}
