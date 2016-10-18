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
	}

}
