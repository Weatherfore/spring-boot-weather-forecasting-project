package spring.boot.weather.forecasting.model;

public class Weather {

	private float temp;
	private float minTemp;
	private float maxTemp;
	private String message;
	private String tempDate;
	
	
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
	}
	public float getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(float minTemp) {
		this.minTemp = minTemp;
	}
	public float getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(float maxTemp) {
		this.maxTemp = maxTemp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTempDate() {
		return tempDate;
	}
	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}
	@Override
	public String toString() {
		return "Weather [temp=" + temp + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", message=" + message
				+ ", tempDate=" + tempDate + "]";
	}

	

}
