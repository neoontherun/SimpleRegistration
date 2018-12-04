package com.techtest.validation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techtest.base.BaseTest;

public class PasswordValidatorTest extends BaseTest {
	
	@Autowired
	PasswordValidator pwdValClassUnderTest;

	@Test
	public void testBasicPasswordCreation() {
		Assert.assertTrue(pwdValClassUnderTest.isValid("abcD123", null));
	}
	
	@Test
	public void testWithoutUpperCasePasswordCreation() {
		Assert.assertFalse(pwdValClassUnderTest.isValid("abcd123", null));
	}

	@Test
	public void testWithoutDigitPasswordCreation() {
		Assert.assertFalse(pwdValClassUnderTest.isValid("abcD", null));
	}
	
	@Test
	public void testWithoutMinCharsPasswordCreation() {
		Assert.assertFalse(pwdValClassUnderTest.isValid("1bC", null));
	}
	
	@Test
	public void testWithSpacePasswordCreation() {
		Assert.assertFalse(pwdValClassUnderTest.isValid("abcD 123", null));
	}
	
	@Test
	public void testWithoutLowerCasePasswordCreation() {
		Assert.assertFalse(pwdValClassUnderTest.isValid("ABCD123", null));
	}

}
