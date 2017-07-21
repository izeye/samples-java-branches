package learningtest.java.util.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tests for {@link ExecutorService#shutdown()} having non-daemon workers
 * with {@link Thread#sleep(long)}.
 *
 * @author Johnny Lim
 */
public class ExecutorServiceSleepShutdownTests {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(() -> {
			try {
				Thread.sleep(5000);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		});
		executorService.shutdownNow();
		System.out.println(new Date() + ": shutdownNow() completed.");
	}

}
