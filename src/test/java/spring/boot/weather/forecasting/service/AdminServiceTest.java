package spring.boot.weather.forecasting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import logging.GlobalResources;
import spring.boot.weather.forecasting.model.Administration;
import spring.boot.weather.forecasting.model.Registration;

@SpringBootTest
public class AdminServiceTest {

	private Logger Logger = GlobalResources.getLogger(AdminServiceTest.class);
	
	@Autowired
	private AdminService adminService;
	
	@Test
	public void testAddAdminSuccess() throws Exception {
		Logger.info("testAddAdminSuccess");
		Administration expected = new Administration(504, "Kavi", "kani");
		Administration actual = adminService.addAdmin(expected);
		assertEquals(expected.getAdminId(), actual.getAdminId());
	}
	
	@Test
	public void testGetAdminSuccess() throws Exception{
		Logger.info("testGetAdminSuccess");
		Administration expected = new Administration(504, "Kavi", "kani");
		Administration actual = adminService.getAdminById(504);
		assertEquals(expected.getAdminId(), actual.getAdminId());
	}
	
}
