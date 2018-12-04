package com.techtest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.techtest.validation.ValidDate;
import com.techtest.validation.ValidPassword;
import com.techtest.validation.ValidUserName;

@Component
@Scope("prototype")
public class UserRegistrationInfo {
	
	String id;

	@NotNull(message="{username.empty}")
	@NotBlank(message="{username.empty}")
	@ValidUserName(message="{username.invalid}")
	String username;

	@NotNull(message="{password.empty}")
	@NotBlank(message="{password.empty}")
	@ValidPassword(message="{password.invalid}")
	String password;

	@NotNull
	@NotBlank
	@ValidDate(message="{date.invalid}")
	String dob;

	@Size(min = 9, max = 9, message="{ssn.invalid}")
	String ssn;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserRegistrationInfo [username=" + username + ", password=" + password + ", dob=" + dob + ", ssn=" + ssn + "]";
	}

}
