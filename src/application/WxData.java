package application;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WxData {	
	private String openWeatherAPI = "http://api.openweathermap.org/data/2.5/weather?q=62237,us&APPID=33d02bf55aa8ffe3a36543eb98e23b40&units=imperial";
	private HttpURLConnection connection = null;	
	private BufferedReader reader; 
	private String line; 
	private StringBuffer responseContent = new StringBuffer(); 
	
	//--constructor
	public WxData() {
	}

	
	public StringBuffer getWxData() {
	
	try { 
		URL url = new URL(openWeatherAPI); 
		connection = (HttpURLConnection) url.openConnection();
	
		connection.setRequestMethod("GET"); 
		connection.setConnectTimeout(5000); // 5 seconds 
		connection.setReadTimeout(5000);
	
		int status = connection.getResponseCode(); 
		
		// prints status code
		System.out.println(status);
		
	
		if(status > 299) { 
			reader = new BufferedReader(new InputStreamReader(connection.getErrorStream())); 
			while((line = reader.readLine()) != null) { 
				responseContent.append(line); 
				}		
			reader.close(); 
		}else { 
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			while((line = reader.readLine()) != null) { 
				responseContent.append(line); 
		  	} 
	  	}
	}catch(MalformedURLException e) { 
		e.printStackTrace(); 
	}catch(IOException e){ 
		e.printStackTrace(); 
	}finally { 
		connection.disconnect(); 
	}
	System.out.println(responseContent);
	return responseContent;
	}

}

	
	



