package learningtest.java.lang;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Thread}.
 *
 * @author Johnny Lim
 */
public class ThreadTests {

	@Test
	public void testInterruptSwallowed() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);

		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					sleep(1000);
				}
				catch (InterruptedException ex) {
					System.out.println("Interrupted.");
					// Do not handle intentionally.
				}

				if (currentThread().isInterrupted()) {
					latch.countDown();
				}
			}
		};
		thread.start();
		Thread.sleep(100);
		thread.interrupt();

		assertThat(latch.await(1, TimeUnit.SECONDS)).isFalse();
	}

	@Test
	public void testInterruptPreserved() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);

		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					sleep(1000);
				}
				catch (InterruptedException ex) {
					System.out.println("Interrupted.");
					currentThread().interrupt();
				}

				if (currentThread().isInterrupted()) {
					latch.countDown();
				}
			}
		};
		thread.start();
		Thread.sleep(100);
		thread.interrupt();

		assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
	}

}
