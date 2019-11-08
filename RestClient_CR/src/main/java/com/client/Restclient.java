package com.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class Restclient {

	    //1.Get Method without Headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get Request
		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpget);  //hit the Get Uri
		return closablehttpresponse;
	}
	
		//2.Get Method with Headers
		public CloseableHttpResponse get(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url); //http get Request
			
			for(Map.Entry<String, String> entry :headermap.entrySet()) {
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
					
			CloseableHttpResponse closablehttpresponse = httpclient.execute(httpget);  //hit the Get Uri
			return closablehttpresponse;
			
	}
	
		//3.Post Method
				public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
					CloseableHttpClient httpclient= HttpClients.createDefault();
					HttpPost httppost = new HttpPost(url);  //http post Request
					httppost.setEntity(new StringEntity(entityString));		//for payloads	
					
					//for headers
					
					for(Map.Entry<String, String> entry :headermap.entrySet()) {
						httppost.addHeader(entry.getKey(),entry.getValue());
				
				}
			
				CloseableHttpResponse closablehttpresponse = httpclient.execute(httppost);
					return closablehttpresponse;
				
				} 

}