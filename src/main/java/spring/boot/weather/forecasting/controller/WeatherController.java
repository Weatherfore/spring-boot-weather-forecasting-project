package spring.boot.weather.forecasting.controller;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.exception.InvalidFieldException;
import spring.boot.weather.forecasting.exception.NoSuchRegistrationException;
import spring.boot.weather.forecasting.exception.WeatherAppException;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.service.RegistrationService;
import spring.boot.weather.forecasting.service.WeatherService;

@RestController
//@CrossOrigin
@RequestMapping(path = "/weather")
public class WeatherController {
	private Logger Logger = GlobalResources.getLogger(WeatherController.class);
	@Autowired
	// @Qualifier("RegistrationService")
	private RegistrationService registrationService;
	@Autowired
	private WeatherService service;

	// localhost:8086/weather/addUser
	@PostMapping(value = "/registration/addUser")
	public ResponseEntity<String> saveUser(@RequestBody Registration newUser) throws InvalidFieldException {
		ResponseEntity<String> response = null;
		if (registrationService.addUser(newUser)) {
			response = new ResponseEntity<String>(newUser.toString(), HttpStatus.CREATED);
		}

		return response;
	}

	// localhost:8086/weather/user/login
	@PostMapping(value = "/user/login")
	public ResponseEntity<Registration> userLogin(@RequestBody Registration user)
			throws IncorrectLoginCredentialsException {
		Registration result = registrationService.loginUser(user.getRid(), user.getPassword());
		ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	//localhost:8086/weather/getUserById/26
	@GetMapping(value = "/getUserById/{rid}")
	public ResponseEntity<Registration> getUserById(@PathVariable("rid") long rid) throws NoSuchRegistrationException {
		Registration result = registrationService.getUser(rid);
		ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	//localhost:8086/weather/deleteUserById/26
	@DeleteMapping(value = "/deleteUserById/{rid}")
	public ResponseEntity<String> deleteUserById(@PathVariable("rid") long rid) throws NoSuchRegistrationException {
		String result = registrationService.deleteUser(rid);
		ResponseEntity<String> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}

	@PutMapping(value = "/updateUser")
	public ResponseEntity<Registration> updateUser(@RequestBody Registration user) throws NoSuchRegistrationException{
		Registration result = registrationService.updatingUser(user);
		ResponseEntity<Registration> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
	}	
		@GetMapping(value = "/getForcast", produces = "application/json")
		public ResponseEntity<Object> getForcast(@RequestParam(value = "cityName") String cityName) {
			this.Logger.info("getForcast called with cityName: "+cityName);
			JSONArray result = null;
			try {
				result = this.service.getForcast(cityName);
				System.out.println(result.toString());
				this.Logger.info("getForcast successfully got resullt for city: "+cityName+" and result is : "+result.toString());
			} catch (WeatherAppException e) {
				this.Logger.error("getForcast Error getting data :  "+e.getMessage());
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<>(result.toString(), HttpStatus.OK);
		}
	
		
		
	
}
