package com.paramount.Utilities;

import java.util.Date;

public class Utilities {
	public static String generateEmailWithTimestamp()
	{
		Date date= new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":","_");
		return "sachinpatil"+timestamp+"@gmail.com";
	}
}
