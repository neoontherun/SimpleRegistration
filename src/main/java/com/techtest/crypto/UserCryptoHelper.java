package com.techtest.crypto;

import javax.annotation.PostConstruct;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserCryptoHelper {

//	StrongTextEncryptor pwdEncDec = new StrongTextEncryptor();
	
	StandardPBEStringEncryptor pwdEncDec = new StandardPBEStringEncryptor();

	@Value("${pwd}")
	String pwd;

	@PostConstruct
	public void initialize() {
		pwdEncDec.setPassword(pwd);
		pwdEncDec.setSaltGenerator(new ZeroSaltGenerator());
	}
	
	public String encryptData(String value) {
		return pwdEncDec.encrypt(value);
	}
	
	public String decryptData(String value) {
		return pwdEncDec.decrypt(value);
	}

}
