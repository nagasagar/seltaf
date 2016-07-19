package com.seltaf.dataobjects;


import java.util.Calendar;

public class FlightSearchinput {
	private String triptype;
	private int no_passengers;
	private String source;
	private String dest;
	private Calendar departuredate;
	private Calendar returndate;
	private String serviceClass;
	private String airlinePreference;
	public String getTriptype() {
		return triptype;
	}
	public void setTriptype(String triptype) {
		this.triptype = triptype;
	}
	public int getNo_passengers() {
		return no_passengers;
	}
	public void setNo_passengers(int no_passengers) {
		this.no_passengers = no_passengers;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public Calendar getDeparturedate() {
		return departuredate;
	}
	public void setDeparturedate(Calendar departuredate) {
		this.departuredate = departuredate;
	}
	public Calendar getReturndate() {
		return returndate;
	}
	public void setReturndate(Calendar returndate) {
		this.returndate = returndate;
	}
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	public String getAirlinePreference() {
		return airlinePreference;
	}
	public void setAirlinePreference(String airlinePreference) {
		this.airlinePreference = airlinePreference;
	}
	
	

}
