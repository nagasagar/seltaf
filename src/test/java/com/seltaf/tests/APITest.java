package com.seltaf.tests;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.PreemptiveBasicAuthScheme;
import com.jayway.restassured.response.Response;


import com.seltaf.core.CustomAssertion;
import com.seltaf.core.SeltafTestPlan;

public class APITest extends SeltafTestPlan {

	/**
	 * test to get capital by country name
	 * @throws JSONException 
	 */
	@Test(groups = { "countries_api", "APITest" }, description = "Continues with test execution even though assertions fail")
	public void getCapital_APITest() throws JSONException {
		 PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
		    auth.setUserName("nds4kor");
		    auth.setPassword("Sagar@12345");

		    
		// make get request to fetch capital of norway
		    Response resp = RestAssured
		            .given()
		            // tried with and without this 
		            .header("Proxy-Authorization", auth.generateAuthToken()) 
		            .proxy("rb-proxy-apac.bosch.com", 8080)
		            .get("http://restcountries.eu/rest/v1/name/norway");
		// Fetching response in JSON
		    System.out.println(resp.asString());
		JSONArray jsonResponse = new JSONArray(resp.asString());
		// Fetching value of capital parameter
		String capital = jsonResponse.getJSONObject(0).getString("capital");
		// Asserting that capital of Norway is Oslo
		CustomAssertion.assertEquals(capital, "Oslo", "Verify Capital is oslo");
	}

}
