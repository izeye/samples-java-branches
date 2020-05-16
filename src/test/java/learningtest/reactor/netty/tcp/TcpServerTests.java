package learningtest.reactor.netty.tcp;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpClient;
import reactor.netty.tcp.TcpServer;

/**
 * Tests for {@link TcpServer}.
 *
 * @author Johnny Lim
 */
class TcpServerTests {

	@Test
	void test() {
		String host = "localhost";
		int port = 18080;
		DisposableServer server = TcpServer.create()
				.host(host)
				.port(port)
				.handle((nettyInbound, nettyOutbound) -> nettyInbound.receive().then())
				.wiretap(true)
				.bindNow();
//		server.onDispose().block();

		Connection connection = TcpClient.create()
				.host(host)
				.port(port)
				.handle((nettyInbound, nettyOutbound) -> nettyOutbound.sendString(Mono.just("hello")))
				.wiretap(true)
				.connectNow();
		connection.onDispose().block();
	}

}
