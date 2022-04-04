package a3_Services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
	private static SimpleDateFormat timeFormatter = new SimpleDateFormat("kk:mm:ss");
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	private static Date d = new Date();
	
	public static String date() {
		d.setTime(System.currentTimeMillis());
		return dateFormatter.format(d);
	}
	
	public static String time() {
		d.setTime(System.currentTimeMillis());
		return timeFormatter.format(d);
	}
	public static void main(String[] args) {
		System.out.println("Es ist " + time() + " Uhr");
		System.out.println("Heute ist der " + date());
	}
	
}
