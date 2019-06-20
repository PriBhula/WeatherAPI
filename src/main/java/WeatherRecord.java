import java.sql.*;

public class WeatherRecord {
	
	private int ID;
	private String city;
	private Date date;
	private double temp;
	private int wind;
	private int rain;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public int getWind() {
		return wind;
	}
	public void setWind(int wind) {
		this.wind = wind;
	}
	
	public int getRain() {
		return rain;
	}
	public void setRain(int rain) {
		this.rain = rain;
	}

}
