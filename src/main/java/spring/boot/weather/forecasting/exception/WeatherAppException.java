package spring.boot.weather.forecasting.exception;

public class WeatherAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WeatherAppException(String message) {
		super(message);

	}

}
