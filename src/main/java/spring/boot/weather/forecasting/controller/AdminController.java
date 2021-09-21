package spring.boot.weather.forecasting.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.AdminNotFoundException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.model.Administration;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.service.AdminService;
import spring.boot.weather.forecasting.service.RegistrationService;

@RestController
@RequestMapping(path = "/weather")
public class AdminController {

	private Logger Logger = GlobalResources.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/addAdmin")
	public ResponseEntity<Administration> addAdministration(@RequestBody Administration administration) {
		Logger.info("getAdministration");
		Administration admin = adminService.addAdmin(administration);
		return new ResponseEntity<Administration>(admin, HttpStatus.OK);
	}

	@GetMapping("/getAdmin/{adminId}")
	public ResponseEntity<Administration> getAdminById(@PathVariable long adminId) throws AdminNotFoundException {
		Logger.info("getAdminById");
		Administration admin = adminService.getAdminById(adminId);
		return new ResponseEntity<Administration>(admin, HttpStatus.OK);
	}

	@GetMapping("/getAllAdmin")
	public List<Administration> getAllAdministration() {
		Logger.info("getAllAdministration");
		return adminService.getAllAdmin();
	}

	@DeleteMapping("/delAdmin/{adminId}")
	public String deleteAdminById(@PathVariable long adminId) throws AdminNotFoundException {
		Logger.info("deleteAdminById");
		return adminService.deleteAdminById(adminId);
	}

	@PatchMapping(value = "/getUserByIdFromAdmin/{rid},")
	public ResponseEntity<Registration> getUserById(@PathVariable("rid") long rid, @RequestBody Administration admin)
			throws NoSuchRegistrationException {
		boolean resultLogin = adminService.loginAdmin(admin.getAdminId(), admin.getAdminPassword(), admin.getAdminName());
		
		if(resultLogin) {
		Registration result = registrationService.getUser(rid);
		ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
		}
		throw new NoSuchRegistrationException("Incorrect Login Credentials");
	}

	@PatchMapping(value = "/getAllUsers")
	public List<Registration> getAllUsers(@RequestBody Administration admin) throws NoSuchRegistrationException{
		boolean resultLogin = adminService.loginAdmin(admin.getAdminId(), admin.getAdminPassword(), admin.getAdminName());
		
		if(resultLogin) {
		Logger.info("getAllUsers");
		return adminService.getAllUsers();
		}
		throw new NoSuchRegistrationException("Incorrect Login Credentials");
	}
}
