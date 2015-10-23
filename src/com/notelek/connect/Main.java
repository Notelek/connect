package com.notelek.connect;


public class Main {
	
	public static void main(String[] args){
		try {
			WebInterface WebInterface = new WebInterface();
			Layout layout = new Layout();
			WebInterface.registerPage("/", layout.generateDashboard("Notelek Connect"));
			PortListener server = new PortListener(80, WebInterface, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
