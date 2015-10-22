package com.notelek.connect;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
 
public class PortListener
{
 
    private static final int fNumberOfThreads = 100;
    private static final Executor fThreadPool = Executors.newFixedThreadPool(fNumberOfThreads);
 
    public PortListener(int port, String response) throws Exception{
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
                    Handler.HandleRequest(connection, response);
                }
            };
            fThreadPool.execute(task);
        }
    }
 
}