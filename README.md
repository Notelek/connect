# Notelek Connect
Java-Based web interface library designed to make interfaces a snap to create.


## Easy Interface Creation
Creating an interface can be a challengin aspect of program design, and take up valuble time that could be spent working on your solution, with Notelek Connect, all you have to do is import the libray, and in as little as 2 lines of code you can have your own web based interface up and running. Check out the tutorials below.

## Tutorials
In two simple lines of java, you can create the base for your interface, doing away with the hassle of setting up a webserver, linking imports, and referencing documentation.

```Java
Layout layout = new Layout();
PortListener server = new PortListener(80, layout.generateDashboard("Notelek Connect"));
```

This example creates the basic page below:
![Notelek Connect Demo Page](http://i.imgur.com/nusC9yN.png)

Where more granular control is required, you can begin to use the deeper functions of connect. Below is the same example as above, but in exapanded form.

```Java
PageBuilder page = new PageBuilder();
page.addNavbar(title, new String[] {"Dashboard", "Logs", "Settings"});
page.addContent("<div style=\"font-size:32pt;text-align:center;width:500px;position:absolute;top:30%;left:50%;margin-left:-250px;\">Dashboard Demo</div>");
String pageContent = page.getPage(title);
```
