package learningtest.reactor.netty.tcp;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.MonoProcessor;
import reactor.netty.DisposableServer;
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
		MonoProcessor<Void> serverDisposed = MonoProcessor.create();
		MonoProcessor<Void> serverConnectionDisposed = MonoProcessor.create();

		DisposableServer server = TcpServer.create()
				.doOnBound((s) -> {
					System.out.println("Bounded: " + s);
					s.onDispose().subscribe(serverDisposed);
				})
				.doOnConnection((connection) -> {
					System.out.println("Connected: " + connection);
					connection.onDispose().subscribe(serverConnectionDisposed);
				})
				.handle((in, out) ->
						in.receive()
								.asString()
								.doOnNext((message) ->
										System.out.println("Message: " + message))
								.then())
				.bindNow();

		Flux<String> flux = Flux.interval(Duration.ofSeconds(1)).map(i -> i + "\n");

		TcpClient.create()
				.port(server.port())
				.handle((in, out) ->
						out.options(NettyPipeline.SendOptions::flushOnEach)
								.sendString(flux)
								.neverComplete())
				.connect()
				.subscribe();

		sleep(5);

		server.disposeNow();
		serverDisposed.block(Duration.ofSeconds(5));

		// NOTE: This will fail due to a Reactor Netty bug.
		// See https://github.com/reactor/reactor-netty/issues/495
		serverConnectionDisposed.block(Duration.ofSeconds(5));

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
