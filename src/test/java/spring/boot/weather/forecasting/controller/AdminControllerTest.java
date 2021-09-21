package spring.boot.weather.forecasting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import logging.GlobalResources;
import spring.boot.weather.forecasting.model.Administration;
import spring.boot.weather.forecasting.model.Registration;

@SpringBootTest
public class AdminControllerTest {

		private Logger Logger = GlobalResources.getLogger(AdminControllerTest.class);
		
		@Autowired
		private AdminController adminController;
		
		@Test
		public void testAddAdminController() throws Exception {
			Logger.info("testAddAdminController");
			HttpStatus expected = HttpStatus.OK;
			Administration newAdmin = new Administration(501, "Leader", "admin10");
			ResponseEntity<Administration> actual = adminController.addAdministration(newAdmin);
			assertEquals(expected, actual.getStatusCode());
		}
		
		@Test
		public void testAddAdminControllerCode() throws Exception {
			Logger.info("testAddAdminControllerCode");
			int expected = 200;
			Administration newAdmin = new Administration(502, "Pavan", "admin11");
			ResponseEntity<Administration> actual = adminController.addAdministration(newAdmin);
			assertEquals(expected, actual.getStatusCodeValue());
		}
		
		@Test
		public void testGetAdminController() throws Exception {
			Logger.info("testAddAdminController");
			HttpStatus expected = HttpStatus.OK;
			ResponseEntity<Administration> actual = adminController.getAdminById(501);
			assertEquals(expected, actual.getStatusCode());
		}
		

		@Test
		public void testGetAdminControllerCode() throws Exception {
			Logger.info("testGetAdminControllerCode");
			int expected = 200;
			ResponseEntity<Administration> actual = adminController.getAdminById(501);
			assertEquals(expected, actual.getStatusCodeValue());
		}
		
		@Test
		public void testDeleteAdminController() throws Exception {
			Logger.info("testDeleteAdminController");
			HttpStatus expected = HttpStatus.OK;
			ResponseEntity<String> actual = adminController.deleteAdminById(502);
			assertEquals(expected, actual.getStatusCode());
		}
		
		@Test
		public void testGetUserFromAdmin() throws Exception{
			Logger.info("testGetUserFromAdmin");
			HttpStatus expected = HttpStatus.OK;
			Administration newAdmin = new Administration(501, "Leader", "admin10");
			ResponseEntity<Registration> actual = adminController.getUserById(101, newAdmin);
			assertEquals(expected, actual.getStatusCode());	
		}
		

		@Test
		public void testGetUserFromAdminCode() throws Exception{
			Logger.info("testGetUserFromAdminCode");
			int expected = 200;
			Administration newAdmin = new Administration(501, "Leader", "admin10");
			ResponseEntity<Registration> actual = adminController.getUserById(101, newAdmin);
			assertEquals(expected, actual.getStatusCodeValue());	
		}

		@Test
		public void testGetAllUserFromAdmin() throws Exception{
			Logger.info("testGetAllUserFromAdmin");
			HttpStatus expected = HttpStatus.OK;
			Administration newAdmin = new Administration(501, "Leader", "admin10");
			ResponseEntity<List<Registration>> actual = adminController.getAllUsers(newAdmin);
			assertEquals(expected,  actual.getStatusCode());	
		}
		
		@Test
		public void testGetAllUserFromAdminCode() throws Exception{
			Logger.info("testGetAllUserFromAdminCode");
			int expected = 200;
			Administration newAdmin = new Administration(501, "Leader", "admin10");
			ResponseEntity<List<Registration>> actual = adminController.getAllUsers(newAdmin);
			assertEquals(expected,  actual.getStatusCodeValue());	
		}
		
		@Test
		public void testGetAllAdmin() throws Exception{
			Logger.info("testGetAllAdmin");
			HttpStatus expected = HttpStatus.OK;
			ResponseEntity<List<Administration>> actual = adminController.getAllAdministration();
			assertEquals(expected,  actual.getStatusCode());	
		}
		
		@Test
		public void testGetAllAdminCode() throws Exception{
			Logger.info("testGetAllAdminCode");
		    int expected = 200;
			ResponseEntity<List<Administration>> actual = adminController.getAllAdministration();
			assertEquals(expected,  actual.getStatusCodeValue());	
		}
		
		
}

