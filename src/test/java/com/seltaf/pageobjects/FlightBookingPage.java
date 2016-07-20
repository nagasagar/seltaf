package com.seltaf.pageobjects;

import org.openqa.selenium.By;

import com.seltaf.core.SeltafPageObject;
import com.seltaf.dataobjects.Address;
import com.seltaf.dataobjects.CreditCard;
import com.seltaf.dataobjects.Passenger;
import com.seltaf.webelements.ButtonElement;
import com.seltaf.webelements.ImageElement;
import com.seltaf.webelements.SelectList;
import com.seltaf.webelements.TextFieldElement;

public class FlightBookingPage extends SeltafPageObject {
	private static ImageElement identifier_element = new ImageElement("Book a Flight Image",By.cssSelector("img[src='/images/masts/mast_book.gif']"));
	
// passenger elements
	 private static TextFieldElement passenger_FirstNameTextBox = new TextFieldElement("passenger firstname Text Box", By.name("passFirst0"));
	 private static TextFieldElement passenger_LastNameTextBox= new TextFieldElement("passenger lastname Text Box", By.name("passLast0"));
	 private static SelectList passenger_MealPreference = new SelectList("meal preference drop down", By.name("pass.0.meal"));
//	credit card elements
	 private static TextFieldElement cc_FirstNameTextBox = new TextFieldElement("CC first name Text Box", By.name("cc_frst_name"));
	 private static TextFieldElement cc_MiddleNameTextBox = new TextFieldElement("cc middle name Text Box", By.name("cc_mid_name"));
	 private static TextFieldElement cc_LastNameTextBox = new TextFieldElement("cc last name Text Box", By.name("cc_last_name"));
	 private static TextFieldElement cc_number = new TextFieldElement("cc number Text Box", By.name("creditnumber"));
	 private static SelectList cc_tvpe = new SelectList("Card type drop down", By.name("creditCard"));
	 private static SelectList cc_exp_year = new SelectList("expiry year drop down", By.name("cc_exp_dt_yr"));
	 private static SelectList cc_exp_month = new SelectList("expiry month drop down", By.name("cc_exp_dt_mn"));
//	billing address
	 private static TextFieldElement b_address1TextBox = new TextFieldElement("billing adress1 Text Box", By.name("billAddress1"));
	 private static TextFieldElement b_address2TextBox = new TextFieldElement("billing adress2 Text Box", By.name("billAddress2"));
	 private static TextFieldElement b_cityTextBox = new TextFieldElement("billing city Text Box", By.name("billCity"));
	 private static TextFieldElement b_stateTextBox = new TextFieldElement("billing state Text Box", By.name("billState"));
	 private static TextFieldElement b_postalcodeTextBox = new TextFieldElement("billing zip Text Box", By.name("billZip"));
	 private static SelectList b_countryDropDown = new SelectList("billing country drop down", By.name("billCountry"));
//		delivery address
	 private static TextFieldElement d_address1TextBox = new TextFieldElement("delivery address1 Text Box", By.name("delAddress1"));
	 private static TextFieldElement d_address2TextBox = new TextFieldElement("delivery address2 Text Box", By.name("delAddress2"));
	 private static TextFieldElement d_cityTextBox = new TextFieldElement("delivery city Text Box", By.name("delCity"));
	 private static TextFieldElement d_stateTextBox = new TextFieldElement("delivery state Text Box", By.name("delState"));
	 private static TextFieldElement d_postalcodeTextBox = new TextFieldElement("delivery zip Text Box", By.name("delZip"));
	 private static SelectList d_countryDropDown = new SelectList("delivery country drop down", By.name("delCountry"));
	 
	 private static ButtonElement SecureBuyButton = new ButtonElement("Continue Button", By.name("buyFlights"));
	
	 public FlightBookingPage() throws Exception {
			super(identifier_element);
		}
	 
	 public FlightBookingPage enter_passenger_information(Passenger p)
	 {
		 passenger_FirstNameTextBox.clearAndType(p.getFirstName());
		 passenger_LastNameTextBox.clearAndType(p.getLastName());
		 passenger_MealPreference.selectByValue(p.getMealPreference());
		 return this;
	 }
	
	 public FlightBookingPage enter_creditcard_information(CreditCard c)
	 {
		 cc_tvpe.selectByText(c.getCardtype());
		 cc_number.clearAndType(c.getCardnumber());
		 cc_exp_month.selectByValue(c.getCardexpiry().split(" ")[0]);
		 cc_exp_year.selectByValue(c.getCardexpiry().split(" ")[1]);
		 cc_FirstNameTextBox.clearAndType(c.getFirstName());
		 cc_MiddleNameTextBox.clearAndType(c.getLastName());
		 cc_LastNameTextBox.clearAndType(c.getMiddleName());
		 return this;
	 }
	 public FlightBookingPage enter_billingaddress_information(Address a)
	 {
		if(a!=null)
		{
			b_address1TextBox.clearAndType(a.getAddress1());
			b_address2TextBox.clearAndType(a.getAddress2());
			b_cityTextBox.clearAndType(a.getCity());
			b_stateTextBox.clearAndType(a.getState());
			b_postalcodeTextBox.clearAndType(a.getCode());
			b_countryDropDown.selectByValue(a.getCountry());
		}
		return this;
	 }
	 public FlightBookingPage enter_deliveryaddress_information(Address a)
	 {
		 if(a!=null)
		{
			 d_address1TextBox.clearAndType(a.getAddress1());
			 d_address2TextBox.clearAndType(a.getAddress2());
			 d_cityTextBox.clearAndType(a.getCity());
			 d_stateTextBox.clearAndType(a.getState());
			 d_postalcodeTextBox.clearAndType(a.getCode());
			 d_countryDropDown.selectByValue(a.getCountry());
		}
		return this;
	 }
	 
	 public BookingConfirmationPage proceed_2_book() throws Exception
	 {
		 SecureBuyButton.click();
		 return new BookingConfirmationPage();
	 }
	
	
	

}
