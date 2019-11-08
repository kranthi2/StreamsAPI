package com.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.apibase;
import com.client.Restclient;
import com.utill.TestUtill;

public class GetApiTest extends apibase{
	apibase apiBase;
	String apiurl;
	String serviceUrl;
	String url;
	Restclient restclient;
	CloseableHttpResponse closablehttpresponse;
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		apiBase = new apibase();
		serviceUrl= prop.getProperty("URL");
		apiurl = prop.getProperty("ServiceURL");
		
		url = serviceUrl+apiurl;
		System.out.println(url);
	}
	
	 
	@Test
	public void getAPITestwithheaders() throws ClientProtocolException, IOException {
		restclient = new Restclient();
		
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content_type", "application/json");
		headermap.put("Authorization", "Bearer 100.h4QM1TpfSyc2aGOq37GEYdrTb2ZJM6N5");
		
		closablehttpresponse = restclient.get(url,headermap);
		
		//a)Status code
		int statuscode = closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code==>"+statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b)Json String
		String responseString =EntityUtils.toString(closablehttpresponse.getEntity(),"UTF-8");
		
		JSONObject responsejson = new JSONObject(responseString);
		System.out.println("response json from API"+responsejson);
		
		//get the value from json Array
		String size= TestUtill.getValueByJPath(responsejson, "/records[0]/attachments[0]/size");
		
		System.out.println(size);
		
		//c)All Headers
		Header[] headerArray= closablehttpresponse.getAllHeaders();
		
		HashMap<String, String> allheaders = new HashMap<String, String>();
			for(Header header: headerArray) {
				allheaders.put(header.getName(), header.getValue());
			}
		System.out.println("Headers Array==>"+allheaders);
	}
	 
}