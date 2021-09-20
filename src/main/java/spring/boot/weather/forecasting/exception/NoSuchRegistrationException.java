package spring.boot.weather.forecasting.exception;

public class NoSuchRegistrationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSuchRegistrationException(String message) {
		super(message);

	}
}