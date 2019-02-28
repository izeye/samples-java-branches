package learningtest.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link ServerSocket}.
 *
 * @author Johnny Lim
 */
public class ServerSocketTests {

	@Test
	@Ignore
	public void test() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("Listen: " + serverSocket.getLocalPort());

		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("Connected: " + socket);

			new Thread(() -> handleClient(socket)).start();
		}
	}

	private void handleClient(Socket socket) {
		try (InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
