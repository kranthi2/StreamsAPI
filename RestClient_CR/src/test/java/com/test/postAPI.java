package com.test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.apibase;
	import com.client.Restclient;
	import com.data.Users;
	import com.fasterxml.jackson.core.JsonGenerationException;
	import com.fasterxml.jackson.databind.JsonMappingException;
	import com.fasterxml.jackson.databind.ObjectMapper;

	public class postAPI extends apibase{
		apibase apiBase;
		String apiurl;
		String serviceUrl;
		String url;
		String authurl;
		String authuri;
		Restclient restclient;
		CloseableHttpResponse closablehttpresponse;
		String client_id="rR3fgoerIdspbwL4";
		String Client_secret="7IZOkCsdP1X5B4nF58IPG0Db4o32JrTb";
		
		
		@BeforeMethod
		public void setUp() throws ClientProtocolException, IOException {
			apiBase = new apibase();
			serviceUrl= prop.getProperty("URL");
			apiurl = prop.getProperty("ServiceURL");
			authuri= prop.getProperty("authuri");
			authurl= prop.getProperty("authurl");
			url = authurl+authuri;
			System.out.println("url is "+url);
		}
		
		@Test
		public void postApitest() throws JsonGenerationException, JsonMappingException, IOException {
			restclient = new Restclient();
			String base64encode= client_id+":"+Client_secret;
			String AccessTokenGetter= Base64.getEncoder().encodeToString(base64encode.getBytes());
			HashMap<String, String> headermap = new HashMap<String, String>();
			headermap.put("Content-Type", "application/x-www-form-urlencoded");
			headermap.put("Accept", "application/json");
			headermap.put("Authorization", "Basic "+AccessTokenGetter);
			
			System.out.println("Basic "+AccessTokenGetter);
			
			
			//jackson API
			ObjectMapper mapper = new ObjectMapper();
			Users users = new Users("security", "abc@12345", "password");
			
			//object to java
			mapper.writeValue(new File("F:\\Automation\\RestClient_CR\\src\\main\\java\\com\\data\\data.json"), users);
			
			//object to json in String
			String usersjosnstring = mapper.writeValueAsString(users);
			System.out.println("payloads are "+usersjosnstring);
			
			closablehttpresponse = restclient.post(url, usersjosnstring, headermap);
			
			//1.status Code
			int statuscode = closablehttpresponse.getStatusLine().getStatusCode();
			System.out.println(statuscode);
			org.apache.http.StatusLine statusline= closablehttpresponse.getStatusLine();
			System.out.println(statusline);
			//Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200);
			
			//2.jsonString
			
			String responsestring = EntityUtils.toString(closablehttpresponse.getEntity(), "UTF-8");
			
	//		 JSONObject responseJson = new JSONObject(responsestring);
			System.out.println("the response API is  "+responsestring);
		
		
			//json to java object
			Users userResObj = mapper.readValue(responsestring, Users.class);
			System.out.println(userResObj);
			
			
		}

}
