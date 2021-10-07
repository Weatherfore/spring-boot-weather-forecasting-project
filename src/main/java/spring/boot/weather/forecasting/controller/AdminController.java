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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.AdminNotFoundException;
import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.exception.InvalidFieldException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.model.Administration;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.service.AdminService;
import spring.boot.weather.forecasting.service.RegistrationService;

@RestController
@RequestMapping(path = "/weather/admin")
public class AdminController {

	private Logger Logger = GlobalResources.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private RegistrationService registrationService;

	// Admin registration
	// localhost:8086/weather/admin/addAdmin

	@PostMapping(value = "/addAdmin")
	public ResponseEntity<String> saveAdmin(@RequestBody Administration admin) throws InvalidFieldException {
		ResponseEntity<String> response = null;
		if (adminService.addAdmin(admin)) {
			response = new ResponseEntity<String>(admin.toString(), HttpStatus.CREATED);
		}

		return response;
	}

	//localhost:8086/weather/admin/-/login
	@PostMapping(path = "/-/login")
	public ResponseEntity<Administration> adminLogin(@RequestBody Administration admin)
			throws IncorrectLoginCredentialsException {
		Administration result = adminService.loginAdmin(admin.getAdminId(), admin.getAdminPassword(), admin.getAdminName());
		ResponseEntity<Administration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	// Find Administration by AdminId
	// localhost:8086/weather/admin/getAdmin/101
	@GetMapping("/getAdmin/{adminId}")
	public ResponseEntity<Administration> getAdminById(@PathVariable long adminId) throws AdminNotFoundException {
		Logger.info("getAdminById");
		Administration admin = adminService.getAdminById(adminId);
		return new ResponseEntity<Administration>(admin, HttpStatus.OK);
	}

	// Get details of all the administration
	// localhost:8086/weather/admin/getAllAdmin
	@GetMapping("/getAllAdmin")
	public ResponseEntity<List<Administration>> getAllAdministration() {
		Logger.info("getAllAdministration");
		List<Administration> result = adminService.getAllAdmin();
		ResponseEntity<List<Administration>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	// Deleting administration by Id
	// localhost:8086/weather/admin/delAdmin/201
	@DeleteMapping("/delAdmin/{adminId}")
	public ResponseEntity<String> deleteAdminById(@PathVariable long adminId) throws AdminNotFoundException {
		Logger.info("deleteAdminById");
		String result = adminService.deleteAdminById(adminId);
		ResponseEntity<String> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	// localhost:8086/weather/admin/updateAdmin
	@PutMapping(value = "/updateAdmin")
	public ResponseEntity<Administration> updateAdmin(@RequestBody Administration admin) {
		Administration result = adminService.updatingAdmin(admin);
		ResponseEntity<Administration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	// get User details from the administration using UserId
	// localhost:8086/weather/admin/getUserByIdFromAdmin/101
	@GetMapping(value = "/getUserByIdFromAdmin/{rid}")
	public ResponseEntity<Registration> getUserById(@PathVariable("rid") long rid) throws NoSuchRegistrationException {
		Registration result = registrationService.getUser(rid);
		ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	// localhost:8086/weather/admin/deleteUserByIdFromAdmin/5
	@DeleteMapping(value = "/deleteUserByIdFromAdmin/{rid}")
	public ResponseEntity<String> deleteUserById(@PathVariable("rid") long rid) throws NoSuchRegistrationException {
		String result = adminService.deleteUser(rid);
		ResponseEntity<String> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	// Get All User details from Administration
	// localhost:8086/weather/admin/getAllUsers
	@GetMapping(value = "/getAllUsers")
	public ResponseEntity<List<Registration>> getAllUsersFromAdmin() {

		Logger.info("getAllUsersFromAdmin");
		List<Registration> result = adminService.getAllUsers();
		ResponseEntity<List<Registration>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;

	}
}
