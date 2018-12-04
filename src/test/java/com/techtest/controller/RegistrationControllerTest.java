package com.techtest.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtest.application.Application;
import com.techtest.model.UserRegistrationInfo;
import com.techtest.service.RegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RegistrationService registrationService;

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
		registrationService.clearAllDBEntries();
	}

	@Test
	public void testFirstFindAll_EmptyReponse() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk());
	}

	@Test
	public void testRegister_1() throws Exception {
		mockMvc.perform(put("/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userInfo))).andExpect(status().isCreated());
	}

	@Test
	public void testRegister_2_Duplicate() throws Exception {
		mockMvc.perform(put("/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userInfo))).andExpect(status().isCreated());
		mockMvc.perform(put("/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userInfo))).andExpect(status().isConflict());
	}

	@Test
	public void testSecondFindAll_SingleResponse() throws Exception {
		mockMvc.perform(put("/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userInfo))).andExpect(status().isCreated());
		mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$[0].id", not(nullValue()))).andExpect(jsonPath("$[0].username", is("Karthik")))
		.andExpect(jsonPath("$[0].ssn", is("123456778")));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}