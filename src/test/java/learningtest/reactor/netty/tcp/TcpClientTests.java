package learningtest.reactor.netty.tcp;

import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.netty.NettyPipeline;
import reactor.netty.tcp.TcpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Tests for {@link TcpClient}.
 *
 * @author Johnny Lim
 */
public class TcpClientTests {

	// Run ServerSocketTests.test() first.
	@Test
	@Ignore
	public void test() {
		Flux<String> flux = Flux.interval(Duration.ofSeconds(1)).map(i -> i + "\n");

		TcpClient.create()
				.host("localhost")
				.port(8080)
				.handle((in, out) -> out
							.options(NettyPipeline.SendOptions::flushOnEach)
							.sendString(flux)
							.neverComplete())
				.connect()
				.subscribe();

		sleep(60);
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
