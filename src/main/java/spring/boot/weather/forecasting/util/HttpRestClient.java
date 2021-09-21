package spring.boot.weather.forecasting.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import logging.GlobalResources;
import spring.boot.weather.forecasting.exception.WeatherAppException;
import spring.boot.weather.forecasting.model.*;

@Component
public class HttpRestClient {
	private final Logger Logger = GlobalResources.getLogger(this.getClass());

	public JSONArray getAPIData(String cityName) {
		this.Logger.info("HttpRestClient: getAPIData() with cityName " + cityName);
		JSONArray jsonObject = new JSONArray();
		HttpURLConnection connection = null;
		try {
			URL url = new URL(getURL(cityName));
			this.Logger.info("URL HOST to be executed: " + url.getHost());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(Constants.GET_METHOD);
			connection.setRequestProperty(Constants.ACCEPT_STR, Constants.APPLICATION_JSON);
			if (connection.getResponseCode() == 200) {
				String result = "";
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String output = null;
				while ((output = br.readLine()) != null) {
					result += output + Constants.NEW_LINE_STR;
				}
				jsonObject = parseResponseToJson(result);
			}

		} catch (Exception e) {
			this.Logger.error("HttpRestClient: getAPIData() Error  " + e.getMessage());
			throw new WeatherAppException(e.getMessage());

		} finally {
			this.Logger.info("HttpRestClient: getAPIData() closing connection  ");
			connection.disconnect();
		}
		return jsonObject;

	}

	private JSONArray parseResponseToJson(String response) {
		JSONObject parsedResponse = new JSONObject(response);
		JSONArray listArray = parsedResponse.getJSONArray("list");
		Map<String, List<Weather>> map = new HashMap<>();
		for (int i = 0; i < listArray.length(); i++) {
			JSONObject weatherObj = listArray.getJSONObject(i);
			float temp = (weatherObj.getJSONObject("main").getFloat("temp"));
			float minTemp = (weatherObj.getJSONObject("main").getFloat("temp_min"));
			float maxTemp = (weatherObj.getJSONObject("main").getFloat("temp_max"));
			String rain = (weatherObj.getJSONArray("weather").getJSONObject(0).getString("main"));

			String date = weatherObj.getString("dt_txt");
			String key = date.split("\\s+")[0];
			Weather weather = new Weather();
			weather.setTemp(temp);
			weather.setMaxTemp(maxTemp);
			weather.setMinTemp(minTemp);
			weather.setTempDate(date);
			weather.setMessage(rain);

			if (!map.containsKey(key))
				map.put(key, new ArrayList<Weather>());
			map.get(key).add(weather);

		}
		Map<String, City> responseMap = new HashMap<>();

		for (String key : map.keySet()) {
			List<Weather> list = map.get(key);
			list.sort(Comparator.comparing(Weather::getMaxTemp));
			float maxTemp = convertKelvinToCelsius(list.get(list.size() - 1).getMaxTemp());
			String rain = (list.get(list.size() - 1).getMessage()).equals("Rain") ? "Carry umbrella"
					: "weather is fine";
			float minTemp = convertKelvinToCelsius(list.get(0).getMinTemp());
			rain = (list.get(0).getMessage()).equals("Rain") ? "Carry umbrella" : "";
			City finalResponse = new City();
			finalResponse.setMaxTemp(maxTemp);
			finalResponse.setMinTemp(minTemp);
			finalResponse.setDate(key);
			if (rain.isEmpty())
				finalResponse.setMessage(maxTemp > 40 ? " Use sunscreen lotion" : "weather is fine");
			else if (!rain.isEmpty())
				finalResponse.setMessage(rain);

			responseMap.put((key), finalResponse);

		}
		JSONArray responseArray = new JSONArray();
		JSONObject obj = new JSONObject(responseMap);

		Set<String> keys = obj.keySet();
		LocalDate localDate = LocalDate.now();
		int currentDate = localDate.getDayOfMonth();
		for (String key : keys) {
			int datePart = Integer.parseInt(key.split("-")[2]);

			if (currentDate < datePart && datePart <= currentDate + 3)
				responseArray.put(obj.getJSONObject(key));
		}

		return responseArray;

	}

	private float convertKelvinToCelsius(float kelvin) {
		return (float) (kelvin - 273.15);
	}

	private String getURL(String cityName) {
		return Constants.HOST + "/" + Constants.VERSION + "/forecast?q=" + cityName + "&appid=" + Constants.API_KEY;
	}
}
