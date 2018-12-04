package com.techtest.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.techtest.base.BaseTest;
import com.techtest.exception.UserAlreadyExistsException;
import com.techtest.exception.UserBlackListedException;
import com.techtest.model.UserRegistrationInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationServiceTest extends BaseTest {

	private static final String BLACKLISTED_SSN = "123456789";

	@Autowired
	RegistrationService registrationServiceImpl;

	UserRegistrationInfo userInfo;

	@Before
	public void init() {
		userInfo = new UserRegistrationInfo();
		userInfo.setUsername("Karthik");
		userInfo.setDob("1990-01-01");
		userInfo.setPassword("abcD123");
		userInfo.setSsn("123456778");
	}
	
	@After
	public void tearDown() {
		registrationServiceImpl.clearAllDBEntries();
	}

	@Test
	public void basicRegistrationTest_1() {
		try {
			registrationServiceImpl.register(userInfo);
			Assert.assertTrue(userInfo.getId() != null || userInfo.getId().equals(""));
		} catch (UserBlackListedException | UserAlreadyExistsException e) {
			Assert.fail();
		}
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void basicRegistrationTest_2() throws UserBlackListedException, UserAlreadyExistsException {
		registrationServiceImpl.register(userInfo);
		registrationServiceImpl.register(userInfo);
	}

	@Test(expected = UserBlackListedException.class)
	public void basicRegistrationTest_3() throws UserBlackListedException, UserAlreadyExistsException {
		userInfo.setSsn(BLACKLISTED_SSN);
		registrationServiceImpl.register(userInfo);
	}

	@Test
	public void getAllRegistrationUsersTest() throws UserBlackListedException, UserAlreadyExistsException {
		registrationServiceImpl.register(userInfo);
		List<UserRegistrationInfo> list = registrationServiceImpl.getAllUserInfo();
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertTrue(list.get(0).getSsn().equals("123456778"));
		Assert.assertTrue(list.get(0).getUsername().equals("Karthik"));
		Assert.assertEquals(null, list.get(0).getPassword());
	}

}
