package spring.boot.weather.forecasting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import logging.GlobalResources;

import spring.boot.weather.forecasting.model.Registration;

@SpringBootTest
public class RegistrationServiceTest {
	private Logger Logger = GlobalResources.getLogger(RegistrationServiceTest.class);

	@Autowired
	private RegistrationService registrationService;

	@Test
	public void testAddUserSuccess() throws Exception {
		Logger.info("testAddUserSuccess");
		boolean expected = true;
		Registration newUser = new Registration(101, "Shalini", "root", "root");
		boolean actual = registrationService.addUser(newUser);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUserByIdSuccess() throws Exception {
		Logger.info("testGetUserByIdSuccess");
		Registration expected = new Registration(37, "Bakki", "root", "root");
		Registration actual = registrationService.getUser(37);
		assertEquals(expected.getRid(), actual.getRid());
		assertEquals(expected.getName(), expected.getName());
		assertEquals(expected.getPassword(), expected.getPassword());
		assertEquals(expected.getReEnterPassword(), expected.getReEnterPassword());
	}

	@Test
	public void testDeleteUserByIdSuccess() throws Exception {
		Logger.info("testDeleteUserByIdSuccess");
		String expected = "Registration successfully deleted";
		String actual = registrationService.deleteUser(101);
		assertEquals(expected, actual);
	}

}
