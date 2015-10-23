package com.notelek.connect;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class WebInterface {
	
	List<String> content = new ArrayList<String>();
	List<String> path = new ArrayList<String>();
	
	public WebInterface(){
		
	}
	
	public void registerPage(String loc, String pageContent){
		content.add(pageContent);
		path.add(loc);
	}
	
	public String resolvePage(String loc){
		for (int i=0;i<path.size();i++) {
			if(path.contains(loc)){
				return (String) content.toArray()[i];
			}
        }
		return "";
	}
	
	private static int indexOf(String needle, String[] haystack)
	{
	    for (int i=0; i<haystack.length; i++)
	    {
	        if (haystack[i] != null && haystack[i].equals(needle)
	            || needle == null && haystack[i] == null) return i;
	    }

	    return -1;
	}
}
