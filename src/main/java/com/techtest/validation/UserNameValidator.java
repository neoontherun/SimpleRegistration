package com.techtest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserNameValidator implements ConstraintValidator<ValidUserName, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (!StringUtils.containsWhitespace(value) && StringUtils.isAlphanumeric(value));
	}

}
