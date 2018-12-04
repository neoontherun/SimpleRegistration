package com.techtest.service;

import java.util.List;

import com.techtest.exception.UserAlreadyExistsException;
import com.techtest.exception.UserBlackListedException;
import com.techtest.model.UserRegistrationInfo;

public interface RegistrationService {
	
	void register(UserRegistrationInfo userInfo) throws UserBlackListedException, UserAlreadyExistsException;
	
	List<UserRegistrationInfo> getAllUserInfo();
	
	void clearAllDBEntries();

}
