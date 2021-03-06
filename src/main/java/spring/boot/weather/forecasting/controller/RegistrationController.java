package spring.boot.weather.forecasting.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.exception.InvalidFieldException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.model.Administration;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.service.RegistrationService;


@RestController
@CrossOrigin
@RequestMapping(path = "/weather")
public class RegistrationController {
		
	@Autowired
	private RegistrationService registrationService;
	

	//Registration of User
	// localhost:8086/weather/registration/addUser
	@PostMapping(value = "/registration/addUser")
	public ResponseEntity<String> saveUser(@RequestBody Registration newUser) throws InvalidFieldException {
		ResponseEntity<String> response = null;
		if (registrationService.addUser(newUser)) {
			response = new ResponseEntity<String>(newUser.toString(), HttpStatus.CREATED);
		}

		return response;
	}
	
	//user login
	//localhost:8086/weather/user/-/login
		@PostMapping(path = "/user/-/login")
		public ResponseEntity<Registration> userLogin(@RequestBody Registration user)
				throws IncorrectLoginCredentialsException {
			Registration result = registrationService.loginUser(user.getRid(), user.getPassword(), user.getUserName(), user.getReEnterPassword());
			ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
			return response;
		}
		
	//Find User using UserId
	//localhost:8086/weather/getUserById/27
		
	@GetMapping(value = "/getUserById/{rid}")
	public ResponseEntity<Registration> getUserById(@PathVariable("rid") long rid) throws NoSuchRegistrationException {
		Registration result = registrationService.getUser(rid);
		ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	//Deleting user using userId
	//localhost:8086/weather/deleteUserById/26
	@DeleteMapping(value = "/deleteUserById/{rid}")
	public ResponseEntity<String> deleteUserById(@PathVariable("rid") long rid) throws NoSuchRegistrationException {
		String result = registrationService.deleteUser(rid);
		ResponseEntity<String> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	//Updating user details using userId
	//localhost:8086/weather/updateUser
	@PutMapping(value = "/updateUser")
	public ResponseEntity<Registration> updateUser(@RequestBody Registration user){
		Registration result = registrationService.updatingUser(user);
		ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}	
					
}
