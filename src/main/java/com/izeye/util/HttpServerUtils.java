package com.izeye.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by izeye on 16. 1. 26..
 */
public abstract class HttpServerUtils {
	
	public static void runEchoServer(int port) {
		new Thread(new EchoServer(port)).start();
	}
	
	private static class EchoServer implements Runnable {
		
		private final int port;
		
		public EchoServer(int port) {
			this.port = port;
		}

		@Override
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				Socket socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				int b;
				while ((b = is.read()) != -1) {
					System.out.print((char) b);
				}
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		
	}
	
}
