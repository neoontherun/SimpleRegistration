package com.techtest.validation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techtest.base.BaseTest;

public class UserNameValidatorTest extends BaseTest {
	
	@Autowired
	UserNameValidator usrNameValClassUnderTest;

	@Test
	public void testBasicUserNameCreation() {
		Assert.assertTrue(usrNameValClassUnderTest.isValid("abcD123", null));
	}
	
	@Test
	public void testWithSpaceUserNameCreation() {
		Assert.assertFalse(usrNameValClassUnderTest.isValid("abcD 123", null));
	}
	
	@Test
	public void testWithSplCharUserNameCreation() {
		Assert.assertFalse(usrNameValClassUnderTest.isValid("abcd*123", null));
	}
	
	@Test
	public void testWithSlashUserNameCreation() {
		Assert.assertFalse(usrNameValClassUnderTest.isValid("abcd\\d23", null));
	}
	
	@Test
	public void testWithTabCharUserNameCreation() {
		Assert.assertFalse(usrNameValClassUnderTest.isValid("abcd\\t23", null));
	}

}
