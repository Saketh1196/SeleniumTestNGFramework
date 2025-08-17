package com.automation.utils;

import java.util.Date;

public class Utilities {
	
	public static final int implicit_wait_time = 10;
	public static final int page_load_time = 10;
	
	public static String generateEmailwithTimeStamp() {
		
		Date date = new Date();
		String timestamp = date.toString().replace(" ","_").replace(":","_");
		return "abcdef06" + timestamp + "@gmail.com";
	}

}
