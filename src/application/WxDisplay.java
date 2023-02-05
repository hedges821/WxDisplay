package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.*;

public class WxDisplay extends Application {
	int displayWidth = 720;
	int displayHeight = 480;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = new StackPane();
			Rectangle rectBackGround = new Rectangle(displayWidth, displayHeight);
			rectBackGround.setFill(Color.rgb( 18, 23, 81));
			
			BorderPane bPane = new BorderPane();
			root.getChildren().addAll(rectBackGround, bPane);			
			
			Scene scene = new Scene(root, displayWidth, displayHeight);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//--Labels
			Label nameLabel = new Label("Coulterville");
			
			//--stacked with image
			Label descriptionLabel = new Label();	
			
			//--"main temp" label and background
			Label mainTempLabel = new Label();
			StackPane mainTempStack = new StackPane();
			Rectangle mainTempRect = new Rectangle();
			setMainTempRectProperties(mainTempRect);
			mainTempStack.getChildren().addAll(mainTempRect, mainTempLabel);
			
			//--"feels like" label and background
			Label mainFeelsLikeLabel = new Label();	
			StackPane mainFeelsLikeStack = new StackPane();
			Rectangle mainFeelsLikeRect = new Rectangle();
			setMainTempRectProperties(mainFeelsLikeRect);
			mainFeelsLikeStack.getChildren().addAll(mainFeelsLikeRect, mainFeelsLikeLabel);
			
					
			Label sunriseLabel = new Label();
			Label sunsetLabel = new Label();
			Label rain1hLabel = new Label();
			rain1hLabel.getStyleClass().add("smallerFont");
			Label rain3hLabel = new Label();
			rain3hLabel.getStyleClass().add("smallerFont");
			Label snow1hLabel = new Label();
			snow1hLabel.getStyleClass().add("smallerFont");
			Label snow3hLabel = new Label();
			snow3hLabel.getStyleClass().add("smallerFont");
			
			Label windSpeedLabel = new Label();
			Label windDirectionLabel = new Label();
			Label humidityLabel = new Label();
			

			//--Get JSON data for JSONWxData class
			JSONWxData jSONWxData = new JSONWxData();
			descriptionLabel.setText(jSONWxData.getWeatherDescription());			
			ImageView imageView = getWxIcon(jSONWxData.getWeatherIcon());			
			
			mainTempLabel.setText(String.format("%s %.1f", "Current Temp:", jSONWxData.getMainTemp()));				
			mainFeelsLikeLabel.setText(String.format("%s %.1f", "Feels like:", jSONWxData.getFeelsLikeTemp()));	
			tempLabelColor(mainTempLabel, mainTempRect, jSONWxData.getMainTemp());
			tempLabelColor(mainFeelsLikeLabel, mainFeelsLikeRect, jSONWxData.getFeelsLikeTemp());			

			humidityLabel.setText(String.format("%s %.1f", "Humidity:", jSONWxData.getHumidity()));
			
			//--displays only if there's rain
			try {
				rain1hLabel.setText(String.format("%s %.1f", "Rain 1 hour:", jSONWxData.getRain1h()));
				
				rain3hLabel.setText(String.format("%s %.1f",  "Rain 3 hours:", jSONWxData.getRain3h()));
			}catch(JSONException e) {
				e.printStackTrace();
			}
			//--displays only if there's snow
			try {
				snow1hLabel.setText(String.format("%s %.1f", "Snow 1 hour:", jSONWxData.getSnow1h()));
				
				snow3hLabel.setText(String.format("%s %.1f", "Snow 3 hours:", jSONWxData.getSnow3h()));				
			}catch(JSONException e) {
				e.printStackTrace();
			}	
			//--gets wind
			try {
				windSpeedLabel.setText(String.format("%s %.1f", "Wind Speed:", jSONWxData.getWind()));
			}catch(JSONException e) {
				e.printStackTrace();
			}
			try {				
				windDirectionLabel.setText("Winds from: " + jSONWxData.getWindDir());
			}catch(JSONException e) {
				e.printStackTrace();
			}
			
			sunriseLabel.setText("Sunrise: " + String.valueOf(convertUtcTime(jSONWxData.getSunrise())) + "am");
			sunsetLabel.setText("Sunset: " + String.valueOf(convertUtcTime(jSONWxData.getSunset())) + "pm");
						
			
			Button exitButton = new Button("Exit");
			exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
				System.exit(0);
			});
			
			
			//--top
			HBox topPane = new HBox(nameLabel);
			topPane.getStyleClass().add("tophbox");
			
			StackPane wxImageAndDescription = new StackPane();
			wxImageAndDescription.getChildren().addAll(imageView, descriptionLabel);
			wxImageAndDescription.setAlignment(Pos.TOP_CENTER);
			
			//--center
			GridPane centerPane = new GridPane();
			centerPane.setHgap(18);
			centerPane.setVgap(8);
			centerPane.setAlignment(Pos.CENTER_LEFT);
			centerPane.getStyleClass().add("centerpane");
			//(node, column index, row index, column span, row span)
			centerPane.add(mainTempStack, 0, 0);
			centerPane.add(mainFeelsLikeStack, 0, 1);
			centerPane.add(rain1hLabel, 0, 2);
			centerPane.add(rain3hLabel, 0, 3);
			centerPane.add(snow1hLabel, 0, 4);
			centerPane.add(snow3hLabel, 0, 5);
			centerPane.add(humidityLabel, 1, 1);
			
			centerPane.add(windSpeedLabel, 1, 2);
			centerPane.add(windDirectionLabel, 1, 3);
			centerPane.add(sunriseLabel, 1, 4);
			centerPane.add(sunsetLabel, 1, 5);
			centerPane.add(wxImageAndDescription, 1, 0, 1, 2);
						
			//--bottom
			HBox bottomPane = new HBox(exitButton);
			bottomPane.getStyleClass().add("bottomhbox");
			
			
			//--BorderPane layout
			bPane.setTop(topPane);
			//bPane.setLeft(leftPane);
			bPane.setCenter(centerPane);
			//bPane.setRight(rightPane);
			bPane.setBottom(bottomPane);	
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//--converts UTC time to local time
	public static String convertUtcTime(int utcTime) {
		Date date = new Date(utcTime * 1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); // 12 hour format (HH is 24 hour format)
		String formatted = sdf.format(date);
		return formatted;
	}
		
			
	public static ImageView getWxIcon(String wxIcon) {
		Image image = null;
		try {
			image = new Image(new FileInputStream("wxIcons/" + wxIcon + "@2x.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.setPreserveRatio(true);
		return imageView;
	}
	
	public static void tempLabelColor(Label label, Rectangle rect, double temp) {
		if(temp > 95) {
			label.setId("tooHotTemp");
			rect.setId("tooHotTemp");
		}
		else if(temp > 85) {
			label.setId("hotTemp");
			rect.setId("hotTemp");
		}
		else if(temp > 55) {
			label.setId("averageTemp");
			rect.setId("averageTemp");
		}
		else if(temp > 32) {
			label.setId("coldTemp");
			rect.setId("coldTemp");
		}
		else{
			label.setId("freezingTemp");
			rect.setId("freezingTemp");
		}
	}
	
	public static void setMainTempRectProperties(Rectangle rectangle) {
		rectangle.setArcHeight(25);
		rectangle.setArcWidth(25);
		rectangle.setHeight(55);
		rectangle.setWidth(280);
		rectangle.getStyleClass().add("rectangle");
	}

}

