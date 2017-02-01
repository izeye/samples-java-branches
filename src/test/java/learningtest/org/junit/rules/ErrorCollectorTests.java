package learningtest.org.junit.rules;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * Tests for {@link ErrorCollector}.
 *
 * @author Johnny Lim
 */
public class ErrorCollectorTests {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Ignore
	@Test
	public void testAddError() {
		try {
			throw new RuntimeException("first thing went wrong");
		}
		catch (Throwable ex) {
			this.collector.addError(ex);
		}

		try {
			throw new RuntimeException("second thing went wrong");
		}
		catch (Throwable ex) {
			this.collector.addError(ex);
		}
	}

}
