package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class apibase {

	public int RESPONSE_STATUS_CODE_200= 200;
	public int RESPONSE_STATUS_CODE_201= 201;
	public int RESPONSE_STATUS_CODE_400= 400;
	public int RESPONSE_STATUS_CODE_404= 404;
	public int RESPONSE_STATUS_CODE_500= 500;
	
	
	public Properties prop;
	
	public apibase() {
		try {
			prop= new Properties();
			FileInputStream ip =new FileInputStream("F:\\Automation\\RestClient_CR\\src\\main\\java\\com\\config\\config.properties");
			prop.load(ip);	
		
			System.out.println(prop.getProperty("authurl"));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
