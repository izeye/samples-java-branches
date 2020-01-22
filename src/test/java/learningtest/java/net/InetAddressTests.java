package learningtest.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link InetAddress}.
 *
 * @author Johnny Lim
 */
public class InetAddressTests {

	@Test
	public void getHostAddress() throws UnknownHostException {
		long startTimestampInMillis = System.currentTimeMillis();
		InetAddress localHost = InetAddress.getLocalHost();
		long elapsedTimeInMillis = System.currentTimeMillis() - startTimestampInMillis;
		System.out.println(elapsedTimeInMillis);

		String hostName = localHost.getHostName();
		System.out.println(hostName);

		String hostAddress = localHost.getHostAddress();
		System.out.println(hostAddress);
	}

	@Test
	public void getAllByName() throws UnknownHostException {
		InetAddress[] addresses = InetAddress.getAllByName("naver.com");
		for (InetAddress address : addresses) {
			System.out.println(address.getHostAddress());
		}
	}

	@Test
	public void isSiteLocalAddress() throws UnknownHostException {
		assertThat(InetAddress.getByName("10.1.2.3").isSiteLocalAddress()).isTrue();
		assertThat(InetAddress.getByName("11.1.2.3").isSiteLocalAddress()).isFalse();
		assertThat(InetAddress.getByName("172.16.1.2").isSiteLocalAddress()).isTrue();
		assertThat(InetAddress.getByName("173.16.1.2").isSiteLocalAddress()).isFalse();
		assertThat(InetAddress.getByName("192.168.1.2").isSiteLocalAddress()).isTrue();
		assertThat(InetAddress.getByName("193.168.1.2").isSiteLocalAddress()).isFalse();
	}

}
