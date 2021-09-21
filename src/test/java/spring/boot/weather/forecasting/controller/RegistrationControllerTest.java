package spring.boot.weather.forecasting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import logging.GlobalResources;
import spring.boot.weather.forecasting.model.Registration;

@SpringBootTest
public class RegistrationControllerTest {

	private Logger Logger = GlobalResources.getLogger(RegistrationControllerTest.class);
	
	@Autowired
	private RegistrationController registrationController;
	
	@Test
	public void testAddUserControllerSuccess() throws Exception {
		Logger.info("testAddUserControllerSuccess");
		HttpStatus expected = HttpStatus.CREATED;
		Registration newUser = new Registration(101, "Shalini", "root", "root");
		ResponseEntity<String> actual = registrationController.saveUser(newUser);
		assertEquals(expected, actual.getStatusCode());
	}
	
	@Test
	public void testAddUserController() throws Exception {
		Logger.info("testAddUserControllerSuccess");
		HttpStatus expected = HttpStatus.ACCEPTED;
		Registration newUser = new Registration(102, "Avila", "avil", "avil");
		ResponseEntity<String> actual = registrationController.saveUser(newUser);
		assertNotEquals(expected, actual.getStatusCode());
	}
	
	
	   
	
	@Test
	public void testGetUserByIdSuccess() throws Exception{
		Logger.info("testGetUserByIdSuccess");
		HttpStatus expected = HttpStatus.OK;
		ResponseEntity<Registration> actual = registrationController.getUserById(101);
		assertEquals(expected, actual.getStatusCode());
		
	}
	
	
	@Test
	public void testGetUserByIdSuccessCode() throws Exception{
		Logger.info("testGetUserByIdSuccessCode");
		int expected = 200;
		ResponseEntity<Registration> actual = registrationController.getUserById(101);
		assertEquals(expected, actual.getStatusCodeValue());
		
	}
	
	@Test
	public void testDeleteUserByIdSuccess() throws Exception{
		Logger.info("testDeleteUserByIdSuccess");
		HttpStatus expected = HttpStatus.OK;
		ResponseEntity<String> actual = registrationController.deleteUserById(101);
		assertEquals(expected, actual.getStatusCode());		
	}
	
	@Test
	public void testAddUserControllerSuccessCode() throws Exception {
		Logger.info("testAddUserControllerSuccessCode");
		int expected = 201;
		Registration newUser = new Registration(90, "Baby", "shalu", "shalu");
		ResponseEntity<String> actual = registrationController.saveUser(newUser);
		assertEquals(expected, actual.getStatusCodeValue());
	}
	
	@Test
	public void testDeleteUserByIdSuccessCode() throws Exception{
		Logger.info("testDeleteUserByIdSuccessCode");
		int expected = 200;
		ResponseEntity<String> actual = registrationController.deleteUserById(90);
		assertEquals(expected, actual.getStatusCodeValue());		
	}
}
