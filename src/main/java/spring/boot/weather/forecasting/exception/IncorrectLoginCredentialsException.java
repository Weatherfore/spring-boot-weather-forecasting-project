package spring.boot.weather.forecasting.exception;

public class IncorrectLoginCredentialsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IncorrectLoginCredentialsException(String message) {
		super(message);
	}
	
}
