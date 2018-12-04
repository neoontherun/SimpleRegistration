package com.techtest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techtest.crypto.UserCryptoHelper;
import com.techtest.entity.UserRegistration;
import com.techtest.exception.UserAlreadyExistsException;
import com.techtest.exception.UserBlackListedException;
import com.techtest.mapper.UserRegistrationMapper;
import com.techtest.model.UserRegistrationInfo;
import com.techtest.repo.UserRegistrationRepository;

@Component
public class RegistrationServiceImpl implements RegistrationService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	ExclusionService exclusionService;

	@Autowired
	UserRegistrationRepository userRegistrationRepository;

	@Autowired
	UserRegistrationMapper userRegistrationMapper;
	
	@Autowired
	UserCryptoHelper cryptoHelper;

	@Override
	public void register(UserRegistrationInfo userInfo) throws UserBlackListedException, UserAlreadyExistsException {
		if (!exclusionService.validate(null, userInfo.getSsn())) {
			if (null == userRegistrationRepository.findUserRegistrationByssn(cryptoHelper.encryptData(userInfo.getSsn()))) {
				UserRegistration entity = userRegistrationMapper.mapFromInfo(userInfo);
				userRegistrationRepository.save(entity);
				userInfo.setId(entity.getId().toString());
			} else {
				logger.error("User found to be already registered!!");
				throw new UserAlreadyExistsException();
			}
		} else {
			logger.error("User found to be blacklisted!!");
			throw new UserBlackListedException();
		}
	}

	@Override
	public List<UserRegistrationInfo> getAllUserInfo() {
		return userRegistrationMapper.mapFromEntityList(userRegistrationRepository.findAll());
	}

	@Override
	public void clearAllDBEntries() {
		userRegistrationRepository.deleteAll();		
	}

}
