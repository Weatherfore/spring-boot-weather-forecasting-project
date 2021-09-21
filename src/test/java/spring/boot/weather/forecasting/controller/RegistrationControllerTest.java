package spring.boot.weather.forecasting.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import logging.GlobalResources;
import spring.boot.weather.forecasting.controller.RegistrationController;

@SpringBootTest
public class RegistrationControllerTest {

	private Logger Logger = GlobalResources.getLogger(RegistrationControllerTest.class);
	
	@Autowired
	private RegistrationController registrationController;
	
	@Test
	public void testAddUserSuccess() throws Exception {
		
	}
	
}
