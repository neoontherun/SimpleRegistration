package com.techtest.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.techtest.crypto.UserCryptoHelper;
import com.techtest.entity.UserRegistration;
import com.techtest.model.UserRegistrationInfo;

@Component
public class UserRegistrationMapper {

	@Autowired
	UserCryptoHelper cryptoHelper;

	public UserRegistration mapFromInfo(UserRegistrationInfo infoObj) {
		UserRegistration entity = new UserRegistration();
		entity.setUsername(infoObj.getUsername());
		entity.setPassword(cryptoHelper.encryptData(infoObj.getPassword()));
		entity.setDob(infoObj.getDob());
		entity.setSsn(cryptoHelper.encryptData(infoObj.getSsn()));
		return entity;
	}

	public UserRegistrationInfo mapFromEntity(UserRegistration entity) {
		UserRegistrationInfo infoObj = new UserRegistrationInfo();
		infoObj.setUsername(entity.getUsername());
		infoObj.setDob(entity.getDob());
		infoObj.setSsn(cryptoHelper.decryptData(entity.getSsn()));
		infoObj.setId(entity.getId().toString());
		return infoObj;
	}

	public List<UserRegistration> mapFromInfoList(List<UserRegistrationInfo> infoObjList) {
		List<UserRegistration> entityList = new ArrayList<>();
		for (UserRegistrationInfo userInfo : infoObjList) {
			entityList.add(mapFromInfo(userInfo));
		}
		return entityList;
	}

	public List<UserRegistrationInfo> mapFromEntityList(List<UserRegistration> entityList) {
		List<UserRegistrationInfo> infoObjList = new ArrayList<>();
		for (UserRegistration userEntity : entityList) {
			infoObjList.add(mapFromEntity(userEntity));
		}
		return infoObjList;
	}

}