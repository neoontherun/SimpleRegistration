package com.techtest.validation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("dateValidator")
public class DateValidator implements ConstraintValidator<ValidDate, String> {

	@Value("${date.format}")
	String dateFormat;
	
	@Value("${age.limit}")
	int ageLimit;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		DateTimeFormatter dtf = new DateTimeFormatterBuilder().parseStrict().append(DateTimeFormatter.ofPattern(dateFormat)).toFormatter().withResolverStyle(ResolverStyle.STRICT);
		try {
			if (Period.between(LocalDate.parse(value, dtf), LocalDate.now()).getYears() >= ageLimit) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Bean
	public String getDateFormat() {
		return dateFormat;
	}

}
