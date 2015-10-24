package com.notelek.connect;

public class Connect {
	
	public static String getVersion(){
		return "v0.0.2 alpha release";
	}
	
	public static void printStartupInfo(){
		Log.info("Initilizing Connect "+getVersion());
	}
	
}
