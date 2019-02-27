package learningtest.reactor.netty.tcp;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.netty.NettyPipeline;
import reactor.netty.tcp.TcpClient;
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
		AtomicReference<Integer> portReference = new AtomicReference<>();
		TcpServer.create()
				.doOnConnection(
						(connection) -> System.out.println("Connected: " + connection))
				.doOnBound((server) -> portReference.set(server.port()))
				.handle((in, out) ->
						in.receive()
								.asString()
								.doOnNext((message) ->
										System.out.println("Message: " + message))
								.then())
				.bindNow();

		Flux<String> flux = Flux.interval(Duration.ofSeconds(1)).map(i -> i + "\n");

		TcpClient.create()
				.port(portReference.get())
				.handle((in, out) ->
						out.options(NettyPipeline.SendOptions::flushOnEach)
								.sendString(flux)
								.neverComplete())
				.connect()
				.subscribe();
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
