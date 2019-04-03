package learningtest.reactor.netty.tcp;

import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import reactor.netty.NettyPipeline;
import reactor.netty.tcp.TcpClient;
import reactor.util.concurrent.Queues;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Tests for {@link TcpClient}.
 *
 * @author Johnny Lim
 */
public class TcpClientTests {

	// Run ServerSocketTests.test() first.
	@Test
	@Ignore
	public void testWithFlux() {
		Flux<String> flux = Flux.interval(Duration.ofSeconds(1)).map(i -> i + "\n");

		AtomicReference<TcpClient> tcpClientReference = new AtomicReference<>();
		TcpClient tcpClient = TcpClient.create()
				.host("localhost")
				.port(8080)
				.handle((in, out) -> out
						.options(NettyPipeline.SendOptions::flushOnEach)
						.sendString(flux)
						.neverComplete())
				.doOnDisconnected((connection) -> connectAndSubscribe(tcpClientReference.get()));
		tcpClientReference.set(tcpClient);
		connectAndSubscribe(tcpClient);

		sleep(60);
	}

	// Run ServerSocketTests.test() first.
	@Test
	@Ignore
	public void testWithUnicastProcessor() {
		UnicastProcessor<String> processor = UnicastProcessor.create(Queues.<String>unboundedMultiproducer().get());
		Flux.interval(Duration.ofSeconds(1)).map(i -> i + "\n").subscribe((m) -> processor.onNext(m));
		Flux<String> flux = Flux.from(processor);

		AtomicReference<TcpClient> tcpClientReference = new AtomicReference<>();
		TcpClient tcpClient = TcpClient.create()
				.host("localhost")
				.port(8080)
				.handle((in, out) -> out
						.options(NettyPipeline.SendOptions::flushOnEach)
						.sendString(flux)
						.neverComplete())
				.doOnDisconnected((connection) -> {
					// The following exception will be thrown when trying to reconnect:
					// java.lang.IllegalStateException: UnicastProcessor allows only a single Subscriber
					System.out.println("doOnDisconnected: " + connection);
					connectAndSubscribe(tcpClientReference.get());
				});
		tcpClientReference.set(tcpClient);
		connectAndSubscribe(tcpClient);

		sleep(60);
	}

	private void connectAndSubscribe(TcpClient tcpClient) {
		tcpClient
				.connect()
				.retryBackoff(Long.MAX_VALUE, Duration.ofSeconds(1), Duration.ofMinutes(1))
				.subscribe();
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