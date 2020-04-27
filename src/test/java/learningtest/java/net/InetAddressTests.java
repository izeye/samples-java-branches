package learningtest.java.net;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link InetAddress}.
 *
 * @author Johnny Lim
 */
class InetAddressTests {

	@Test
	void detectSlowGetLocalHost() throws UnknownHostException {
		long startTimestampInMillis = System.currentTimeMillis();
		InetAddress localHost = InetAddress.getLocalHost();
		long elapsedTimeInMillis = System.currentTimeMillis() - startTimestampInMillis;
		System.out.println("Elapsed time: " + elapsedTimeInMillis + " ms");
		if (elapsedTimeInMillis > 200) {
			System.out.println("Add the following entries to '/etc/hosts':");
			System.out.println("127.0.0.1 localhost " + localHost.getHostName());
			System.out.println("::1 localhost " + localHost.getHostName());
		}
	}

	@Test
	void getHostAddress() throws UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();
		String hostAddress = localHost.getHostAddress();
		System.out.println("hostAddress: " + hostAddress);
	}

	@Test
	void getAllByName() throws UnknownHostException {
		InetAddress[] addresses = InetAddress.getAllByName("naver.com");
		for (InetAddress address : addresses) {
			System.out.println(address.getHostAddress());
		}
	}

	@Test
	void isSiteLocalAddress() throws UnknownHostException {
		assertThat(InetAddress.getByName("10.1.2.3").isSiteLocalAddress()).isTrue();
		assertThat(InetAddress.getByName("11.1.2.3").isSiteLocalAddress()).isFalse();
		assertThat(InetAddress.getByName("172.16.1.2").isSiteLocalAddress()).isTrue();
		assertThat(InetAddress.getByName("173.16.1.2").isSiteLocalAddress()).isFalse();
		assertThat(InetAddress.getByName("192.168.1.2").isSiteLocalAddress()).isTrue();
		assertThat(InetAddress.getByName("193.168.1.2").isSiteLocalAddress()).isFalse();
	}

}
