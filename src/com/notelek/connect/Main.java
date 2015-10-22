package com.notelek.connect;


public class Main {
	
	public static void main(String[] args){
		try {
			Layout layout = new Layout();
			@SuppressWarnings("unused")
			PortListener server = new PortListener(80, layout.generateDashboard("Notelek Connect"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
