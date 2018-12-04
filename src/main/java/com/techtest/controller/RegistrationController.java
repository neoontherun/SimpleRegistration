package com.techtest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.exception.UserAlreadyExistsException;
import com.techtest.exception.UserBlackListedException;
import com.techtest.model.UserRegistrationInfo;
import com.techtest.service.RegistrationService;

@RestController
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	RegistrationService registrationService;

	@PutMapping("/register")
	public ResponseEntity<UserRegistrationInfo> register(@Valid @RequestBody UserRegistrationInfo userRegInfo) {
		logger.info("Received incoming message : {} ", userRegInfo);
		try {
			registrationService.register(userRegInfo);
		} catch (UserBlackListedException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(userRegInfo, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserRegistrationInfo>> getAllRegisteredUsers() {
		List<UserRegistrationInfo> userInfoList = registrationService.getAllUserInfo();
		return new ResponseEntity<>(userInfoList, HttpStatus.OK);
	}
}