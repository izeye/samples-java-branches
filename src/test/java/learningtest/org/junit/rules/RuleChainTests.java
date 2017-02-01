package learningtest.org.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;

/**
 * Tests for {@link RuleChain}.
 *
 * @author Johnny Lim
 */
public class RuleChainTests {

	private static class LoggingRule extends ExternalResource {

		private final String name;

		public LoggingRule(String name) {
			this.name = name;
		}

		@Override
		protected void before() throws Throwable {
			System.out.println("before " + this.name);
		}

		@Override
		protected void after() {
			System.out.println("after " + this.name);
		}

	}

	@Rule
	public RuleChain chain = RuleChain
			.outerRule(new LoggingRule("outer rule"))
			.around(new LoggingRule("middle rule"))
			.around(new LoggingRule("inner rule"));

	@Test
	public void test() {
	}

}
