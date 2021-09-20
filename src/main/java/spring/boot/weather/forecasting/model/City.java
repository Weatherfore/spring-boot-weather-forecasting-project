package spring.boot.weather.forecasting.model;



public class City {

	String city;
	float minTemp;
	float maxTemp;
	String message;
	String date;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Response [city=" + city + ", minTemp=" + minTemp + ", maxTemp=" + maxTemp + ", message=" + message
				+ ", date=" + date + "]";
	}

	

}
