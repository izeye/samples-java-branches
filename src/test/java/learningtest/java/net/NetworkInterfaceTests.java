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
		System.out.println(localHost);

		NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
		System.out.println(networkInterface);

		// With Gradle, "networkInterface" is null but why?
		// But setting "127.0.0.1       localhost Johnnyui-MacBook-Pro.local" to "/etc/hosts" works.
		if (networkInterface == null) {
			System.err.println("networkInterface is null");
			return null;
		}

		// With Gradle and setting "127.0.0.1       localhost #Johnnyui-MacBook-Pro.local" to "/etc/hosts"
		// makes "hardwareAddress" "null".
		byte[] hardwareAddress = networkInterface.getHardwareAddress();
		if (hardwareAddress == null) {
			System.err.println("hardwareAddress is null");
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (byte b : hardwareAddress) {
			sb.append(String.format("%02x", b));
			sb.append(':');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
