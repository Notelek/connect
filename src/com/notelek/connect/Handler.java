package com.notelek.connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler {
	public static void HandleRequest(Socket s, WebInterface intFace)
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
        	out.println("HTTP/4.0 200");
            out.println("Content-type: text/html");
            out.println("Server-name: NotelekConnectServer");
            try{
                out.println("Content-length: " + intFace.resolvePage(request.split(" ")[1]).length());
            }catch(Exception e){
            }
            out.println("");
            out.println(intFace.resolvePage(request.split(" ")[1]));
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
