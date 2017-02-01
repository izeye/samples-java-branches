package learningtest.org.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * Tests for {@link TestLogger}.
 *
 * @author Johnny Lim
 */
public class TestLoggerTests {

	@Rule
	public TestLogger logger = new TestLogger();

	@Test
	public void test() {
		Logger logger = this.logger.getLogger();
		logger.info("Hello, world!");
	}

}
