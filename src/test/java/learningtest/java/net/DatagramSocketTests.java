package learningtest.java.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link DatagramSocket}.
 *
 * @author Johnny Lim
 */
public class DatagramSocketTests {

	@Ignore
	@Test
	public void test() throws IOException {
		DatagramSocket socket = new DatagramSocket(9125);
		byte[] buffer = new byte[1024];
		while (true) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			System.out.println(new String(packet.getData(), 0, packet.getLength()));
		}
	}

}
