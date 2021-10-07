package spring.boot.weather.forecasting.exception;

public class EmptyRecordException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmptyRecordException(String message) {
		super(message);
	}
	
}
