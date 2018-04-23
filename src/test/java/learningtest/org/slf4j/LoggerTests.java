package learningtest.org.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for {@link Logger}.
 *
 * @author Johnny Lim
 */
public class LoggerTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testParameterizedMessage() {
		this.logger.debug("This is a test.");

		// Will use `void debug(String format, Object arg);`.
		this.logger.debug("My name is {}.", "Johnny");

		RuntimeException ex = new RuntimeException("Test exception");
		// Will use `void debug(String msg, Throwable t);`.
		// So `{}` doesn't work as expected.
		this.logger.debug("Unexpected exception: {}", ex);
	}

	@Test
	public void testExtraLastThrowableParameter() {
		RuntimeException ex = new RuntimeException("Test exception");
		this.logger.error("My name is {}.", "Johnny", ex);
	}

}
