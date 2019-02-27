package learningtest.reactor.netty.tcp;

import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import reactor.netty.tcp.TcpServer;

/**
 * Tests for {@link TcpServer}.
 *
 * @author Johnny Lim
 */
public class TcpServerTests {

	@Ignore
	@Test
	public void test() {
		TcpServer.create()
				.doOnConnection(
						(connection) -> System.out.println("Connected: " + connection))
				.handle((in, out) ->
						in.receive()
								.asString()
								.doOnNext((message) ->
										System.out.println("Message: " + message))
								.then())
				.bindNow();

		sleep(30);
	}

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}
	}

}
