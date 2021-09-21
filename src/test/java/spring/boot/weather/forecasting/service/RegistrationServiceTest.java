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
		Registration newUser = new Registration(103, "Monu", "ruvy", "ruvy");
		boolean actual = registrationService.addUser(newUser);
		assertEquals(expected, actual);
	}

	@Test
	public void testAddUserSuccessForGet() throws Exception {
		Logger.info("testAddUserSuccessForGet");
		boolean expected = true;
		Registration newUser = new Registration(104, "Sonu", "ruvy", "ruvy");
		boolean actual = registrationService.addUser(newUser);
		assertEquals(expected, actual);
	}

	
	@Test
	public void testGetUserByIdSuccess() throws Exception {
		Logger.info("testGetUserByIdSuccess");
		Registration expected = new Registration(104, "Sonu", "ruvy", "ruvy");
		Registration actual = registrationService.getUser(104);
		assertEquals(expected.getRid(), actual.getRid());
	}
	
	@Test
	public void testGetUserByIdCheckingName() throws Exception {
		Logger.info("testGetUserByIdCheckingName");
		Registration expected = new Registration(104, "Sonu", "ruvy", "ruvy");
		Registration actual = registrationService.getUser(104);
		assertEquals(expected.getUserName(), actual.getUserName());
	}

	@Test
	public void testGetUserByIdCheckingPassword() throws Exception {
		Logger.info("testGetUserByIdCheckingPassword");
		Registration expected = new Registration(104, "Sonu", "ruvy", "ruvy");
		Registration actual = registrationService.getUser(104);
		assertEquals(expected.getPassword(), actual.getPassword());
	}
	
	@Test
	public void testDeleteUserByIdSuccess() throws Exception {
		Logger.info("testDeleteUserByIdSuccess");
		String expected = "Registration successfully deleted";
		String actual = registrationService.deleteUser(103);
		assertEquals(expected, actual);
	}

}
