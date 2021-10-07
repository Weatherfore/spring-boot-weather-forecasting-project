package spring.boot.weather.forecasting.service;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.GlobalResources;
//import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.exception.WeatherAppException;
import spring.boot.weather.forecasting.repository.RegistrationRepository;
import spring.boot.weather.forecasting.util.HttpRestClient;

@Service
public class WeatherService {
	private final Logger Logger = GlobalResources.getLogger(this.getClass());

	@Autowired
	private HttpRestClient restClient;
	@Autowired
	RegistrationRepository registrationRepository;

	public JSONArray getForcast(String cityName) throws WeatherAppException {

		this.Logger.info("Service:getForcast called with cityName:  " + cityName);
		String regex = "^[A-Za-z ]+";
		if (cityName.matches(regex)) {
			JSONArray response = this.restClient.getAPIData(cityName);
			this.Logger.info("Service:getForcast successfully return data with cityName:  " + cityName);
			return response;
		}
		throw new WeatherAppException("city name is invalid");
	}

}
