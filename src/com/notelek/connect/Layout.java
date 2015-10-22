package com.notelek.connect;

public class Layout {

	public Layout(){

	}

	public String generateDashboard(String title){
		PageBuilder page = new PageBuilder();
		page.addNavbar(title, new String[] {"Dashboard", "Logs", "Settings"});
		page.addContent("<div style=\"font-size:32pt;text-align:center;width:500px;position:absolute;top:30%;left:50%;margin-left:-250px;\">Dashboard Demo</div>");
		String pageContent = page.getPage(title);
		return pageContent;
	}

}
