package spring.boot.weather.forecasting.service;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.IncorrectLoginCredentialsException;
import spring.boot.weather.forecasting.repository.RegistrationRepository;
import spring.boot.weather.forecasting.util.HttpRestClient;

@Service
public class WeatherService {

	private final Logger Logger = GlobalResources.getLogger(this.getClass());

	@Autowired
	private HttpRestClient restClient;
	@Autowired
	RegistrationRepository registrationRepository;

	public JSONArray getForcast(String cityName) throws IncorrectLoginCredentialsException {
		// boolean userLogging = false;

		this.Logger.info("Service:getForcast called with cityName:  " + cityName);
		JSONArray response = this.restClient.getAPIData(cityName);
		this.Logger.info("Service:getForcast successfully return data with cityName:  " + cityName);
		return response;

	}

	public boolean loginUser(long rid, String password, String name, String reEnterPassword)
			throws IncorrectLoginCredentialsException {
		boolean result = false;
		if (registrationRepository.existsById(rid)
				&& registrationRepository.findById(rid).get().getPassword().equals(password)
				&& registrationRepository.findById(rid).get().getName().equals(name)
				&& registrationRepository.findById(rid).get().getReEnterPassword().equals(reEnterPassword)) {

			Logger.info("User login is Successfull");
			result = true;
			return result;
		}
		Logger.error("details are incorrect");
		throw new IncorrectLoginCredentialsException("Invalid Credentials");	
	}

}
