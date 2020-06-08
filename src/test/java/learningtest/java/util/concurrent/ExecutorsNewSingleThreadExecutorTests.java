package learningtest.java.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Executors#newSingleThreadExecutor()}.
 *
 * @author Johnny Lim
 */
class ExecutorsNewSingleThreadExecutorTests {

	@Test
	void scheduleAtFixedRateWithException() throws InterruptedException {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(() -> {
			System.out.println("Hello, world!");
			throw new RuntimeException("Boom!");
		}, 1, 1, TimeUnit.SECONDS);
		scheduler.awaitTermination(5, TimeUnit.SECONDS);
	}

}
