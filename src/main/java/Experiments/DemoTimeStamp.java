package Experiments;

import java.util.Date;

public class DemoTimeStamp {

	public static void main(String[] args) {
		Date date= new Date();
		System.out.println(date.toString().replace(" ", "_").replace(":","_"));

	}

}
