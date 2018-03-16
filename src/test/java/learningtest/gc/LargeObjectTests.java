package learningtest.gc;

import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Tests for a large object.
 */
public class LargeObjectTests {

	// NOTE: This should be run with a large heap like 8 GB: "-Xms8g -Xmx8g"
	@Ignore
	@Test
	public void test() throws InterruptedException {
		Thread.sleep(TimeUnit.SECONDS.toMillis(60));
		System.out.println("Creating a large object.");

		// 4 GB object.
		int[] largeObject = new int[1_000_000_000];
		System.out.println("Created a large object.");
		Thread.sleep(TimeUnit.SECONDS.toMillis(300));
		System.out.println(largeObject.length);
	}

}
