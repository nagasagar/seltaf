package com.seltaf.tests;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seltaf.core.Filter;
import com.seltaf.core.SeltafTestPlan;
import com.seltaf.dataobjects.FlightSearchinput;
import com.seltaf.helpers.SpreadSheetHelper;
import com.seltaf.pageobjects.BookingConfirmationPage;
import com.seltaf.pageobjects.HomePage;
import com.seltaf.util.internal.entity.TestEntity;
import static com.seltaf.core.CustomAssertion.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MercuryToursFlightBookingTest extends SeltafTestPlan{
	
	@DataProvider(
	        name = "FlightBookingData",
	        parallel = false
	    )
	    public static Iterator<Object[]> getUserInfo(final Method m)
	        throws Exception {
	        Filter filter = Filter.equalsIgnoreCase(TestEntity.TEST_METHOD,
	                m.getName());

	        LinkedHashMap<String, Class<?>> classMap =
	            new LinkedHashMap<String, Class<?>>();
	        classMap.put("TestEntity", TestEntity.class);
	        classMap.put("FlightSearchinput", FlightSearchinput.class);

	        return SpreadSheetHelper.getEntitiesFromSpreadsheet(
	        		MercuryToursFlightBookingTest.class, classMap, "flightsearchinput.csv", filter);
	    }
	    
//	    
	    @Test(
	            groups = { "registerWithValidUserData" },
	            dataProvider = "FlightBookingData",
	            description = "Register with valid login data"
	        )
	        public void flightBookingTest(final TestEntity testEntity,
	            final FlightSearchinput input) throws Exception {

	            BookingConfirmationPage confirmationpage = new HomePage(true).navigateToLoginPage()
	            .login2Website()
	            .Searchflight(input)
	            .select_cheap_outgoing_flight()
	            .select_cheap_return_flight()
	            .continue_with_selected_flight_options()
	            .enter_passenger_information(null)
	            .enter_creditcard_information(null)
	            .enter_billingaddress_information(null)
	            .enter_deliveryaddress_information(null)
	            .proceed_2_book();
	            String conf_num = confirmationpage.getConfirmationNumber();
	            assertThat("User Name text box is missing on Booking Confirmation Page!!!",	conf_num.length()>1, is(true));
	            confirmationpage.logout();

	        }

}
