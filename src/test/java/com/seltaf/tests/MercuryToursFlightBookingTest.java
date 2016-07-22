package com.seltaf.tests;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seltaf.core.Filter;
import com.seltaf.core.SeltafTestPlan;
import com.seltaf.dataobjects.CreditCard;
import com.seltaf.dataobjects.FlightSearchinput;
import com.seltaf.dataobjects.Passenger;
import com.seltaf.helpers.SpreadSheetHelper;
import com.seltaf.pageobjects.BookingConfirmationPage;
import com.seltaf.pageobjects.HomePage;
import com.seltaf.util.internal.entity.TestEntity;

import static com.seltaf.core.CustomAssertion.assertEquals;
import static com.seltaf.core.CustomAssertion.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MercuryToursFlightBookingTest extends SeltafTestPlan{
	
	@DataProvider(
	        name = "FlightBookingData",
	        parallel = true
	    )
	    public static Iterator<Object[]> getData(final Method m)
	        throws Exception {
	        Filter filter = Filter.equalsIgnoreCase(TestEntity.TEST_METHOD,
	                m.getName());

	        LinkedHashMap<String, Class<?>> classMap =
	            new LinkedHashMap<String, Class<?>>();
	        classMap.put("TestEntity", TestEntity.class);
	        classMap.put("FlightSearchinput", FlightSearchinput.class);
	        classMap.put("Passenger", Passenger.class);
	        classMap.put("CreditCard", CreditCard.class);

	        return SpreadSheetHelper.getEntitiesFromSpreadsheet(
	        		MercuryToursFlightBookingTest.class, classMap, "flightsearchinput.csv", filter);
	    }
	    
//	    
	    @Test(
	            groups = { "MercureTours" },
	            dataProvider = "FlightBookingData",
	            description = "Booking of flight tickets"
	        )
	        public void flightBookingTest(final TestEntity testEntity,
	            final FlightSearchinput input, final Passenger passenger, final CreditCard creditcard) throws Exception {

	    		if(passenger.getFirstName().equalsIgnoreCase("nagasagar")||passenger.getFirstName().equalsIgnoreCase("web"))
	    			assertEquals(true, false, passenger.getFirstName()+" is a realuser");
	            BookingConfirmationPage confirmationpage = new HomePage(true).navigateToLoginPage()
	            .login2Website()
	            .Searchflight(input)
	            .select_cheap_outgoing_flight()
	            .select_cheap_return_flight()
	            .continue_with_selected_flight_options()
	            .enter_passenger_information(passenger)
	            .enter_creditcard_information(creditcard)
	            .enter_billingaddress_information(null)
	            .enter_deliveryaddress_information(null)
	            .proceed_2_book();
	            String conf_num = confirmationpage.getConfirmationNumber();
	            assertThat("Confirmation number is missing on Booking Confirmation Page!!!",	conf_num.length()>1, is(true));
	            confirmationpage.logout();

	        }

}
