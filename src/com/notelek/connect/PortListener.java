package com.notelek.connect;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
 
public class PortListener
{
 
    private static final int fNumberOfThreads = 100;
    private static final Executor fThreadPool = Executors.newFixedThreadPool(fNumberOfThreads);
 
    public PortListener(int port, final WebInterface intFace, final boolean acceptOutsideReq) throws Exception{
        @SuppressWarnings("resource")
		ServerSocket socket = new ServerSocket(port);
        socket.setReuseAddress(true);
        while (true)
        {
            final Socket connection = socket.accept();
            Runnable task = new Runnable()
            {
                @Override
                public void run()
                {
                	String webServerAddress = connection.getInetAddress().toString();
                	if(acceptOutsideReq){
                		Handler.HandleRequest(connection, intFace);
                	}else{
                		if(webServerAddress == "/0:0:0:0:0:0:0:1"){
                			Handler.HandleRequest(connection, intFace);
                		}else{
                			Log.info("Blocked Connection From: "+webServerAddress);
                		}
                	}
                }
            };
            fThreadPool.execute(task);
        }
    }
 
}