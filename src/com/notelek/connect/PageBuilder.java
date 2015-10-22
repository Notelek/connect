package com.notelek.connect;

public class PageBuilder {
	
	private String html = null;
	private String leftLayout = null;
	private String rightLayout = null;
	private String leftContent = null;
	private String rightContent = null;
	private String content = null;
	private boolean navAdded = false;
	private boolean layoutAdded = false;
	private boolean leftDisabled = true;
	private boolean rightDisabled = true;
	
	public PageBuilder(){
		
	}
	
	public boolean addNavbar(String title, String[] items){
		if(!navAdded){
			String navElement = "<nav class=\"navbar navbar-inverse navbar-fixed-top\">"+
								"<div class=\"container\">"+
								"<div class=\"navbar-header\">"+
								"<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\"><span class=\"sr-only\">Toggle navigation</span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span><span class=\"icon-bar\"></span></button>"+
								"<a class=\"navbar-brand\" href=\"#\">"+title+"</a>"+
								"<div id=\"navbar\" class=\"collapse navbar-collapse\" style=\"float:right;\">"+
								"<ul class=\"nav navbar-nav\">"+
								"<li class=\"active\"><a href=\"#"+items[0]+"\">"+items[0]+"</a></li>";
			for(int i=1;i<items.length;i++){
				navElement+="<li><a href=\"#"+items[i]+"\">"+items[i]+"</a></li>";
			}
			navElement+="</ul></div></div></nav>";
			html = navElement + html;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addLayout(int right, int left){
		String rightDivEnd = "";
		@SuppressWarnings("unused")
		String leftDivEnd = "";
		if(!layoutAdded){
			layoutAdded = true;
			if(right+left != 12){
				Log.info("ERROR: Layout must be exactly 12 columns wide");
				return false;
			}
			if(right != 0){
				rightDisabled = false;
				if(right < 6){
					rightLayout = "<div class=\"col-sm-"+right+"\">";
				}else{
					rightLayout = "<div class=\"col-sm-"+right+" sidebar\">";
				}
			}else{
				rightDisabled = true;
				rightDivEnd = "</div>";
			}
			if(left != 0){
				leftDisabled = false;
				if(left < 6){
					leftLayout = rightDivEnd+"<div class=\"col-sm-"+left+"\">";
				}else{
					leftLayout = rightDivEnd+"<div class=\"col-sm-"+left+" sidebar\">";
				}
			}else{
				leftDisabled = true;
				leftDivEnd = "</div>";
			}
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addLeftContent(String c){
		if(leftDisabled){
			return false;
		}
		leftContent = c;
		return true;
	}
	
	public String addButton(String type, String text, String name){
		return "<button type=\"button\" class=\"btn btn-"+type+" "+name+"\">"+text+"</button>";
	}
	
	public boolean addRightContent(String c){
		if(rightDisabled){
			return false;
		}
		rightContent = c;
		return true;
	}
	
	public boolean addContent(String c){
		content = c;
		return true;
	}
	

	public String getPage(String title){
		if(layoutAdded){
			if(!leftDisabled && !rightDisabled){
				html += html + "<div class=\"container-fluid\"><div class=\"row\">" + (leftLayout+leftContent+"</div>"+rightLayout+rightContent+"</div></div></div>");
			}
		}else{
			html += html + "<div class=\"container-fluid\"><div class=\"row\">" + content + "</div></div>";
		}
		String upper = "<!DOCTYPE html>"+
					   "<html lang=\"en\">"+
					   		"<head>"+
					   			"<meta charset=\"utf-8\"></meta>"+
					   			"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"></meta>"+
					   			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"></meta>"+
					   			"<title>"+title+"</title>"+
					   			"<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" rel=\"stylesheet\"></link>"+
					   		"</head>"+
					   		"<body>";
		String lower = 			"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>"+
					   			"<script src=\"js/bootstrap.min.js\"></script>"+
					   		"</body>"+
					   	"</html>";
		String[] preIndent = (upper+html+lower).replaceAll(">(?! \r\n)", ">\r\n").split("\r\n");
		String page = "";
		int indentAmount = 1;
		String indent = "";
		for(int i=0;i<preIndent.length;i++){
			if(preIndent[i].contains("</")){
				indentAmount-=1;
			}else{
				indentAmount+=1;
			}
			indent = "";
			for(int y=0;y<(indentAmount-2);y++){
				indent = indent+"	";
			}
			page+=indent+preIndent[i]+"\r\n";
		}
		return page;
	}
	
}
