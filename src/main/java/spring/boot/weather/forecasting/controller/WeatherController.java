package spring.boot.weather.forecasting.controller;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.WeatherAppException;
import spring.boot.weather.forecasting.model.Registration;
import spring.boot.weather.forecasting.service.WeatherService;

@RestController

@RequestMapping(path = "/weather")
public class WeatherController {
	private Logger Logger = GlobalResources.getLogger(RegistrationController.class);

	@Autowired
	private WeatherService service;
	
	// http://localhost:8086/weather/getForcast?cityName=Delhi
	@PatchMapping(value = "/getForcast", produces = "application/json")
	public ResponseEntity<Object> getForcast(@RequestBody Registration user,
			@RequestParam(value = "cityName") String cityName) {

		this.Logger.info("getForcast called with cityName: " + cityName);
		JSONArray result = null;

		try {
			boolean resultLogin = service.loginUser(user.getRid(), user.getPassword(), user.getUserName(),
					user.getReEnterPassword());
			if (resultLogin) {
				result = this.service.getForcast(cityName);
				System.out.println(result.toString());
				this.Logger.info("getForcast successfully got result for city: " + cityName + " and result is : "
						+ result.toString());

			}
		} catch (WeatherAppException e) {
			this.Logger.error("getForcast Error getting data :  " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(result.toString(), HttpStatus.OK);
	}
}
