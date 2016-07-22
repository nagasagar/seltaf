package com.seltaf.dataobjects;

public class CreditCard {

	private String cardtype;
	private String cardnumber;
	private String cardexpiry;
	private String FirstName;
	private String MiddleName;
	private String LastName;
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getCardexpiry() {
		return cardexpiry;
	}
	public void setCardexpiry(String cardexpiry) {
		this.cardexpiry = cardexpiry;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	@Override
	public String toString() {
		
	        return ("CreditCard: ["+
	 " cardtype: " + cardtype +","+
	 "cardnumber:"+cardnumber+","+
	 "cardexpiry :"+cardexpiry+","+
	 "FirstName:"+FirstName+","+
	 "MiddleName:"+MiddleName+","+
	 "LastName:"+LastName+","+ 
	 " ]");
	        
	 }
}
