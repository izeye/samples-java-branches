package learningtest.org.junit.rules;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Tests for {@link Timeout}.
 *
 * @author Johnny Lim
 */
public class TimeoutTests {

	@Rule
	public Timeout timeout = Timeout.millis(20);

	@Ignore
	@Test
	public void run1() throws InterruptedException {
		Thread.sleep(100);
	}

	@Ignore
	@Test
	public void infiniteLoop() {
		while (true) {
		}
	}

}
