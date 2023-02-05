package application;

import org.json.JSONArray;
import org.json.JSONObject;


public class JSONWxData{
	
	//--true  = actual data
	//--false = dummy data
	private boolean data = true;
	
	WxData wxData = new WxData();
	//--Actual Wx data from openweather.org - WxData.java
//  JSONObject jsonObject = new JSONObject(wxData.getWxData().toString());

	//--Dummy data from DummyData.java to run tests with
	DummyData dummyData = new DummyData();
//	JSONObject jsonObject = new JSONObject(dummyData.getDummyData());
	
	//----------------------------------------------------------
	
	//--toggle between the actual data and the dummy data
	JSONObject jsonObject = new JSONObject((data == true)? wxData.getWxData().toString():
		dummyData.getDummyData().toString());

	//----------------------------------------------------------	
	
	//--constructor
	public JSONWxData() {
		
	}	

	public String getWeatherDescription() {
		JSONArray arr = jsonObject.getJSONArray("weather");
		String description = null;
		for (int i = 0; i < arr.length(); i++) { 
			description = arr.getJSONObject(i).getString("description");			
		}
		return description;
	}
	
	public String getWeatherIcon() {
		JSONArray arr = jsonObject.getJSONArray("weather");
		String wxIcon = null;
		for (int i = 0; i < arr.length(); i++) { 			
			wxIcon = arr.getJSONObject(i).getString("icon");	
		}
		return wxIcon;
	}
	
	public double getMainTemp() {
		return jsonObject.getJSONObject("main").getDouble("temp");
	}
	
	public double getFeelsLikeTemp() {
		return jsonObject.getJSONObject("main").getDouble("feels_like");
	}
	
	public double getHumidity() {
		return jsonObject.getJSONObject("main").getDouble("humidity");
	}
	
	//--rain
	public double getRain1h() {
		return jsonObject.getJSONObject("rain").getDouble("1h");
	}
	public double getRain3h() {
		return jsonObject.getJSONObject("rain").getDouble("3h");
	}
	
	//--snow
	public double getSnow1h() {
		return jsonObject.getJSONObject("snow").getDouble("1h");
	}
	public double getSnow3h() {
		return jsonObject.getJSONObject("snow").getDouble("3h");
	}
	
	//--wind
	public double getWind() {
		return jsonObject.getJSONObject("wind").getDouble("speed");
	}
	
	//--wind direction
	public String getWindDir() {
		String windDeg = null;
		double windDirection = jsonObject.getJSONObject("wind").getDouble("deg");
		if(windDirection >= 360) {
			windDeg = "N";
		}
		else if(windDirection >= 337.5) {
			windDeg = "NNE";
		}
		else if(windDirection >= 315) {
			windDeg = "NE";
		}
		else if(windDirection >= 292.5) {
			windDeg = "ENE";
		}
		else if(windDirection >= 270) {
			windDeg = "E";
		}
		else if(windDirection >= 247.5) {
			windDeg = "ESE";
		}
		else if(windDirection >= 225) {
			windDeg = "SE";
		}
		else if(windDirection >= 202.5) {
			windDeg = "SSE";
		}
		else if(windDirection >= 180) {
			windDeg = "S";
		}
		else if(windDirection >= 157.5) {
			windDeg = "SSW";
		}
		else if(windDirection >= 135) {
			windDeg = "SW";
		}
		else if(windDirection >= 112.5) {
			windDeg = "WSW";
		}
		else if(windDirection >= 90) {
			windDeg = "W";
		}
		else if(windDirection >= 67.5) {
			windDeg = "WNW";
		}
		else if(windDirection >= 45) {
			windDeg = "NW";
		}
		else if(windDirection >= 22.5) {
			windDeg = "NNW";
		}
		else if(windDirection == 0) {
			windDeg = "N";
		}
		return windDeg;
	}
	
	public int getSunrise() {
		return jsonObject.getJSONObject("sys").getInt("sunrise");
	}
	
	public int getSunset() {
		return jsonObject.getJSONObject("sys").getInt("sunset");
	}

}
