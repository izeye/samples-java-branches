package learningtest.java.net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Tests for {@link NetworkInterface}.
 *
 * @author Johnny Lim
 */
public class NetworkInterfaceTests {

	@Test
	public void test() throws UnknownHostException, SocketException {
		String macAddress = getMacAddress();
		System.out.println(macAddress);
	}

	private String getMacAddress() throws UnknownHostException, SocketException {
		InetAddress localHost = InetAddress.getLocalHost();
		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);

		// With Gradle, "networkInterface" is null but why?
		if (networkInterface == null) {
			System.err.println("networkInterface is null");
			return null;
		}

		byte[] hardwareAddress = networkInterface.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (byte b : hardwareAddress) {
			sb.append(String.format("%02x", b));
			sb.append(':');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
