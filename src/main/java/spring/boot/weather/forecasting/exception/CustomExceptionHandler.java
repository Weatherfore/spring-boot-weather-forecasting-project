package spring.boot.weather.forecasting.exception;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import logging.GlobalResources;


@ControllerAdvice
public class CustomExceptionHandler {

	private Logger Logger = GlobalResources.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<Object> handleAdminNotFoundException() {
		Logger.error("handleAdminNotFoundException");

		return new ResponseEntity<Object>("Admin Id is Not Present", HttpStatus.NOT_FOUND);

	}

	
	@ExceptionHandler(IncorrectLoginCredentialsException.class)
	public ResponseEntity<Object> handleIncorrectLoginCredentialsException() {
		Logger.error("handleIncorrectLoginCredentialsException");

		return new ResponseEntity<Object>("Login credentials are incorrect", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidFieldException.class)
	public ResponseEntity<Object> handleInvalidFieldException() {
		Logger.error("handleInvalidFieldException");

		return new ResponseEntity<Object>("Details are Incorrect", HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(NoSuchRegistrationException.class)
	public ResponseEntity<Object> handleNoSuchRegistrationException() {
		Logger.error("handleNoSuchRegistrationException");

		return new ResponseEntity<Object>("There is no such registration in this Id", HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(WeatherAppException.class)
	public ResponseEntity<Object> handleWeatherAppException() {
		Logger.error("handleWeatherAppException");

		return new ResponseEntity<Object>("Invalid City", HttpStatus.NOT_FOUND);

	}

	
	
}
