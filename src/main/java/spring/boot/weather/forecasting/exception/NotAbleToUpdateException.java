package spring.boot.weather.forecasting.exception;

public class NotAbleToUpdateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotAbleToUpdateException(String message) {
		super(message);

	}
}
