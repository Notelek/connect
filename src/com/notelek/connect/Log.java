package com.notelek.connect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	public static void info(String message){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println("["+dateFormat.format(date)+"][Connect] "+message);
	}
	
}
