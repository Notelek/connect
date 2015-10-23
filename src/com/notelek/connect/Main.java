package com.notelek.connect;


public class Main {
	
	public static void main(String[] args){
		try {
			WebInterface inf = new WebInterface();
			Layout layout = new Layout();
			inf.registerPage("/", layout.generateDashboard("Notelek Connect"));
			PortListener server = new PortListener(80, inf, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
