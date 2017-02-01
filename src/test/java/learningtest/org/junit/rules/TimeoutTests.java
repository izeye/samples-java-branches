package learningtest.org.junit.rules;

import java.util.concurrent.TimeUnit;

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
	public Timeout timeout = new Timeout(20, TimeUnit.MILLISECONDS);

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
