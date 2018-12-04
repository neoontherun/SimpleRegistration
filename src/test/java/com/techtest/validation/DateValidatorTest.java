package com.techtest.validation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.techtest.base.BaseTest;

public class DateValidatorTest extends BaseTest {
	
	@Autowired
	DateValidator dtValClassUnderTest;

	@Test
	public void testBasicUserRegObjectCreation() {
		Assert.assertTrue(dtValClassUnderTest.isValid("1980-10-10", null));
	}
	
	@Test
	public void testLeapYear() {
		Assert.assertTrue(dtValClassUnderTest.isValid("1980-02-29", null));
	}
	
	@Test
	public void testInvalidDate() {
		Assert.assertFalse(dtValClassUnderTest.isValid("1980-02-30", null));
	}
	
	@Test
	public void testInvalidAgeBARDate() {
		Assert.assertFalse(dtValClassUnderTest.isValid("2001-10-30", null));
	}
	
	@Test
	public void testValidAgeBARDate() {
		Assert.assertTrue(dtValClassUnderTest.isValid("2000-10-22", null));
	}
	
	@Test
	public void testInvalidFormatDate_1() {
		Assert.assertFalse(dtValClassUnderTest.isValid("2000/10/22", null));
	}
	
	@Test
	public void testInvalidFormatDate_2() {
		Assert.assertFalse(dtValClassUnderTest.isValid("10/22/2000", null));
	}

}
