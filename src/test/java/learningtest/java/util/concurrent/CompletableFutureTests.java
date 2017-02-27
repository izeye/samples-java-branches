package learningtest.java.util.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CompletableFuture}.
 *
 * @author Johnny Lim
 */
public class CompletableFutureTests {

	private final Executor executor = Executors.newFixedThreadPool(2);

	@Test
	public void testSupplyAsync() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future =
				CompletableFuture.supplyAsync(delayedValueSupplier(1), this.executor)
						.thenApply(i -> i + 3);
		assertThat(future.get()).isEqualTo(4);
	}

	@Test
	public void testThenCompose() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future =
				CompletableFuture.supplyAsync(delayedValueSupplier(3), this.executor)
						.thenCompose(
								i -> CompletableFuture.supplyAsync(
										delayedValueSupplier(2, i * 100),
										this.executor));
		assertThat(future.get()).isEqualTo(2);
	}

	@Test
	public void testMultiStagePipeline() throws ExecutionException, InterruptedException {
		CompletableFuture<Integer> future =
				CompletableFuture.supplyAsync(delayedValueSupplier(3), this.executor)
						.thenApply(i -> {
							System.out.println("First future completed: " + i);
							return i + 1;
						})
						.thenCompose(
								i -> CompletableFuture.supplyAsync(
										delayedValueSupplier(i + 2, i * 100),
										this.executor));
		assertThat(future.get()).isEqualTo(6);
	}

	private Supplier<Integer> delayedValueSupplier(int value) {
		return delayedValueSupplier(value, 1000);
	}

	private Supplier<Integer> delayedValueSupplier(int value, int delayInMillis) {
		return () -> {
			try {
				Thread.sleep(delayInMillis);
				return value;
			}
			catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		};
	}
}
