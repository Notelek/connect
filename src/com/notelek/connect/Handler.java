package com.notelek.connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler {
	public static void HandleRequest(Socket s, String response)
    {
        BufferedReader in;
        PrintWriter out;
        String request;
 
        try
        {
            String webServerAddress = s.getInetAddress().toString();
            Log.info("New Connection:" + webServerAddress);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
 
            request = in.readLine();
            Log.info("Client request: " + request);
 
            out = new PrintWriter(s.getOutputStream(), true);
            if(request.split(" ")[1] != "/"){
            	out.println("HTTP/4.0 200");
                out.println("Content-type: text/html");
                out.println("Server-name: NotelekConnectServer");
                out.println("Content-length: " + response.length());
                out.println("");
                out.println(response);
            }else{

                System.out.println(request.split(" ")[1]);
            	if(!request.split(" ")[1].contains("..")){
            		File file = new File("www/"+request.split(" ")[1]);
            		FileInputStream fis = new FileInputStream(file);
            		byte[] data = new byte[(int) file.length()];
            		fis.read(data);
            		fis.close();

            		String str = new String(data, "UTF-8");
            		out.println("HTTP/4.0 200");
                    out.println("Content-type: text/html");
                    out.println("Server-name: NotelekConnectServer");
                    out.println("Content-length: " + str.length());
                    out.println("");
                    out.println(str);
            	}
            }
            out.flush();
            out.close();
            s.close();
        }
        catch (IOException e)
        {
        	Log.info("Failed respond to client request: " + e.getMessage());
        }
        finally
        {
            if (s != null)
            {
                try
                {
                    s.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return;
    }
}
