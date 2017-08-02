package learningtest.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * Tests for {@link InetAddress}.
 *
 * @author Johnny Lim
 */
public class InetAddressTests {

	@Test
	public void testGetLocalHost() throws UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();
		String hostName = localHost.getHostName();
		System.out.println(hostName);

		String hostAddress = localHost.getHostAddress();
		System.out.println(hostAddress);
	}

	@Test
	public void testGetAllByName() throws UnknownHostException {
		InetAddress[] addresses = InetAddress.getAllByName("naver.com");
		for (InetAddress address : addresses) {
			System.out.println(address.getHostAddress());
		}
	}

}
