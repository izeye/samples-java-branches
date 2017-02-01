package learningtest.org.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

/**
 * Tests for {@link ExternalResource}.
 *
 * @author Johnny Lim
 */
public class ExternalResourceTests {

	private Server myServer = new Server();

	@Rule
	public ExternalResource resource = new ExternalResource() {

		@Override
		protected void before() throws Throwable {
			myServer.connect();
		}

		@Override
		protected void after() {
			myServer.disconnect();
		}

	};

	@Test
	public void test() {
		new Client().run(this.myServer);
	}

	private static class Server {

		public void connect() {
			System.out.println("Server.connect()");
		}

		public void disconnect() {
			System.out.println("Server.disconnect()");
		}

	}

	private static class Client {

		public void run(Server server) {
			System.out.println("Client.run()");
		}

	}

}
