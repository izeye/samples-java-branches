package learningtest.java.util.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

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
					System.out.println("log(" + index + ") = " + +Math.log(index));
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

}
