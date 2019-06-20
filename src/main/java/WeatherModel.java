/*
 * WeatherModel.java
 * Author: Priyanka Bhula
 * */
import java.sql.*;

import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Calendar;

import nz.co.solnet.helper.DatabaseHelper;

public class WeatherModel {
	
	private Connection connection = null;	
	private DatabaseHelper helper;
	
	public WeatherModel(JFrame parent) {
		helper = new DatabaseHelper();
		DatabaseHelper.cleanDatabase();
		DatabaseHelper.initialiseDB();
		connection = helper.getConnection();
	}
	
	public String fetchAllRecords() {

		String result = "";
		String select = ("SELECT * FROM cities_weather"); //get all records from table
		
		Statement s;
		ResultSet rs;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery(select);
			
			int ID = 0;
			String city = "";
			Date date = new Date(0);
			double temp = 0;
			int wind = 0;
			int rain = 0;						
			
			
			while(rs.next()) {
				ID = rs.getInt("id");
				city = rs.getString("city");
				date = rs.getDate("date");
				temp = rs.getDouble("temperature");
				wind = rs.getInt("wind");
				rain = rs.getInt("rain");
				
				result = result + ID + ": " + city + " on " + date + 
						" was " + temp + "\u00B0C, wind: " + wind +
						"kph" + ", rain: " + rain + "mm\n";
			}
			s.close();
			
		} catch (SQLException e) {
			return "error fetching all data";
		}
		return result;
	}
	
	public String fetchRecord(String toFetch) {
		String result = "";
		String select = ("SELECT * "
				+ "FROM cities_weather "
				+ "WHERE city = '" +toFetch+"'");//get record with same city name
		
		Statement s;
		ResultSet rs;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery(select);
		
			int ID = 0;
			String city = toFetch;
			Date date = new Date(0);
			double temp = 0;
			int wind = 0;
			int rain = 0;
			
			
			if(rs.next()) {
				ID = rs.getInt("id");
				date = rs.getDate("date");
				temp = rs.getDouble("temperature");
				wind = rs.getInt("wind");
				rain = rs.getInt("rain");
				
				result = result + ID + ": " + city + " on " + date + 
						" was " + temp + "\u00B0C, wind: " + wind +
						"kph" + ", rain: " + rain + "mm\n";
			}
			else {
				result = "There are no records of that city";				
			}
			s.close();
			
		} catch (SQLException e) {
			return "error fetching record";
		}
		
		return result;
	}
	
	public String getWarmest() {
		String result = "";
		String select = ("SELECT * "
				+ "FROM cities_weather "
				+ "ORDER BY temperature ASC");
		
		Statement s;
		ResultSet rs;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery(select);
		
			String city = "";
			double temp = 0;
			
			while(rs.next()) {
				city = rs.getString("city");
				temp = rs.getDouble("temperature");
				
				result = city + " was "+ temp +" \u00B0C";
			}
			s.close();
			
		} catch (SQLException e) {
			return "error fetching warmest";
		}
		
		return result;
	}
	
	public String getColdest() {
		String result = "";
		String select = ("SELECT * "
				+ "FROM cities_weather "
				+ "ORDER BY temperature DESC");
		
		Statement s;
		ResultSet rs;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery(select);
		
			String city = "";
			double temp = 0;
			
			while(rs.next()) {
				city = rs.getString("city");
				temp = rs.getDouble("temperature");
				
				result = city + " was "+ temp + "\u00B0C";
			}
			s.close();
			
		} catch (SQLException e) {
			return "error fetching warmest";
		}
		
		return result;
	}

	public String addRecord(String city, Double temp, int wind, int rain) {
		String result = "Added record: ";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(Calendar.getInstance().getTime());
				
		String select = "INSERT INTO cities_weather VALUES (DEFAULT, '"+ city + "', '"+ today +"',"+ temp+","+wind+","+rain+")";

		Statement s;
		
		try {
			s= connection.createStatement();
			s.executeUpdate(select);
			
			result += city;
			s.close();
		} catch(SQLException e) {
			return "error adding record";
		}
		return result;
	}
	
	public String deleteRecord(String city) {
		String result = "Deleted record: ";
		String select = "SELECT * FROM cities_weather WHERE city = '" + city +  "'";
	
		Statement s;
		ResultSet rs;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery(select);
			int count = 0;
			while(rs.next()) {
				count++; //count as many records are for the city (check if there are any)
			}
			if(count==0) {
				return "There are no records of that city";
			}
			
		} catch (SQLException e) {
			return "error finding city";
		}
		
		String delete = "DELETE FROM cities_weather WHERE city = '" + city +"'";
		Statement d;

		
		try {
			d = connection.createStatement();
			d.executeUpdate(delete);
			
			result += city;
			d.close();
		} catch (SQLException e) {
			return "error deleting record";
		}
		return result;
	}
	
	public String modRecord(String city, Double temp, int wind, int rain) {
		String result = "Modified city record: ";
		
		String select = "SELECT * FROM cities_weather WHERE city = '" + city +  "'";
		
		Statement s;
		ResultSet rs;
		
		try {
			s = connection.createStatement();
			rs = s.executeQuery(select);
			int count = 0;
			while(rs.next()) {
				count++;
			}
			if(count==0) {
				return "There are no records of that city";
			}
			
		} catch (SQLException e) {
			return "error finding city";
		}

		String modify = "UPDATE cities_weather SET temperature=" + temp +", wind="+wind +", rain="+rain + " WHERE city = '" + city + "'";		
		Statement m;
		
		try {
			m = connection.createStatement();
			m.executeUpdate(modify);
			System.out.println("executed");
			
			result += city;
			m.close();
		} catch(SQLException e) {
			return "error modifying record";
		}
		return result;
	}
}
