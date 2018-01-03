package learningtest.java.util.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests for {@link Executors}.
 *
 * @author Johnny Lim
 */
public class ExecutorsTests {

	@Test
	public void test() {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;

		List<Future<?>> futures = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			final int index = i;
			Future<?> future = executorService.submit(() -> {
				try {
					Thread.sleep(1000 * index);
					System.out.println("log(" + index + ") = " + Math.log(index));
				}
				catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
			});
			futures.add(future);
			System.out.println("Active count: " + threadPoolExecutor.getActiveCount());
		}

		for (Future<?> future : futures) {
			try {
				future.get();
			}
			catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			catch (ExecutionException ex) {
				throw new RuntimeException(ex);
			}
			System.out.println("Active count: " + threadPoolExecutor.getActiveCount());
		}
	}

	@Test
	public void testException() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);

		// Local variable "callable" is used to avoid the following error:
		// Error:(62, 54) java: reference to submit is ambiguous
		// both method <T>submit(java.util.concurrent.Callable<T>) in java.util.concurrent.ExecutorService and method submit(java.lang.Runnable) in java.util.concurrent.ExecutorService match
		Callable<Void> callable = () -> {
			throw new RuntimeException("Intentional exception.");
		};

		Future<Void> future = executorService.submit(callable);
		try {
			future.get();
			fail();
		}
		catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
		catch (ExecutionException ex) {
			assertThat(ex.getCause().getMessage()).isEqualTo("Intentional exception.");
		}
	}

	@Test
	public void testShutdown() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			try {
				Thread.sleep(TimeUnit.SECONDS.toMillis(5));
			}
			catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			latch.countDown();
		});
		executorService.shutdown();

		assertThat(latch.await(10, TimeUnit.SECONDS)).isTrue();
	}

	@Test
	public void testShutdownNow() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			try {
				Thread.sleep(TimeUnit.SECONDS.toMillis(5));
			}
			catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			latch.countDown();
		});
		executorService.shutdownNow();

		assertThat(latch.await(10, TimeUnit.SECONDS)).isFalse();
	}

}
