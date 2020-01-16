package experiment;

import java.util.Random;

import org.junit.Test;

/**
 * Flaky tests.
 *
 * @author Johnny Lim
 */
public class FlakyTests {

	@Test
	public void test() {
		if (new Random().nextBoolean()) {
			throw new RuntimeException("Expected flakiness!");
		}
	}

}
