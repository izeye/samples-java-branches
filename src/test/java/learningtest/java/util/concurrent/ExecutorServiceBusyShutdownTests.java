package learningtest.java.util.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tests for {@link ExecutorService#shutdown()} having non-daemon workers
 * with a single for-loop.
 *
 * @author Johnny Lim
 */
public class ExecutorServiceBusyShutdownTests {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(() -> {
			double d = 0d;
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				d += Math.sqrt(Integer.MAX_VALUE);
			}
			System.out.println(new Date() + ": " + d);
		});
		executorService.shutdownNow();
		System.out.println(new Date() + ": shutdownNow() completed.");
	}

}
