package application;

public class DummyData {
		
		public String getDummyData() {
			//--variables for data pulled into WxDisplay
			int mainTemp = 105;
			int feelsLikeTemp = 84;
			String descriptionVar = "party clouds";			
			String wxIcon = "11d";
			int humidity = 90;
			double windSpeed = 5.5;
			double windDirection = 215.5;
			double rain1h = 3.3;
			double rain3h = 5.6;
			double snow1h = 1.3;
			double snow3h = 8.3;
				
			return "{\"coord\":"
					+ "{\"lon\":-89.6057,"
					+ "\"lat\":38.1864},"
					+ "\"weather\":[{"
					+ "\"id\":804,"
					+ "\"main\":"
					+ "\"Clouds\","
					+ "\"description\":" + descriptionVar +","
					+ "\"icon\":" + wxIcon + "}],"
					+ "\"base\":\"stations\","
					+ "\"main\":{"
					+ "\"temp\":" + mainTemp + ","
					+ "\"feels_like\":" + feelsLikeTemp + ","
					+ "\"temp_min\":274.42,"
					+ "\"temp_max\":275.19,"
					+ "\"pressure\":1014,"
					+ "\"humidity\":" + humidity + "},"
					+ "\"visibility\":10000,"
					+ "\"wind\":{"
					+ "\"speed\":" + windSpeed + ","
					+ "\"deg\":" + windDirection + "},"
					+ "\"clouds\":{"
					+ "\"all\":100},"
					+ "\"rain\":{"
					+ "\"1h\":" + rain1h + ","
					+ "\"3h\":" + rain3h + "},"
					+ "\"snow\":{"
					+ "\"1h\":" + snow1h + ","
					+ "\"3h\":" + snow3h + "},"
					+ "\"dt\":1674699902,"
					+ "\"sys\":{"
					+ "\"type\":1,"
					+ "\"id\":5774,"
					+ "\"country\":\"US\","
					+ "\"sunrise\":1674652122,"
					+ "\"sunset\":1674688346},"
					+ "\"timezone\":-21600,"
					+ "\"id\":4250476,"
					+ "\"name\":\"St. Louis"
					+ "\",\"cod\":200}\r\n";
	}	
	

}
