package learningtest.org.junit;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for JUnit {@link BeforeClass} timing.
 *
 * @author Johnny Lim
 */
public class JUnitBeforeClassTimingTests {

	private static String value1;
	private static String value2;

	@BeforeClass
	public static void setUp() {
		value1 = getValue(1);
		value2 = getValue(2);
	}

	private static String getValue(int id) {
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}
		return "test-" + id;
	}

	@Test
	public void test1() {
		test(() -> assertThat(value1).isEqualTo("test-1"));
	}

	@Test
	public void test2() {
		test(() -> assertThat(value2).isEqualTo("test-2"));
	}

	private void test(Runnable runnable) {
		long startTimeMillis = System.currentTimeMillis();
		runnable.run();
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		System.out.println("Elapsed time: " + elapsedTimeMillis);
	}

}
