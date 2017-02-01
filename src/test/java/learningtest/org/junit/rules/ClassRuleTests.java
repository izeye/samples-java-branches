package learningtest.org.junit.rules;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Tests for {@link ClassRule}.
 *
 * @author Johnny Lim
 */
@RunWith(Suite.class)
@SuiteClasses({A.class, B.class, C.class})
public class ClassRuleTests {

	private static final Server SERVER = new Server();

	@ClassRule
	public static ExternalResource resource = new ExternalResource() {

		@Override
		protected void before() throws Throwable {
			SERVER.connect();
		}

		@Override
		protected void after() {
			SERVER.disconnect();
		}

	};

	private static class Server {

		public void connect() {
			System.out.println("connect");
		}

		public void disconnect() {
			System.out.println("disconnect");
		}

	}

}
