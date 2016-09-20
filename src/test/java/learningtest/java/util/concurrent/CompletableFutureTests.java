package learningtest.java.util.concurrent;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CompletableFuture}.
 *
 * @author Johnny Lim
 */
public class CompletableFutureTests {

	@Test
	public void test() throws ExecutionException, InterruptedException {
		String s = "Hello";
		Promise<Integer> p = Promise.promise(() -> slowLength(s))
				.flatMap(i -> Promise.promise(() -> slowDouble(i)));
		assertThat(p.get()).isEqualTo(10);
	}

	private int slowLength(String s) {
		someLongComputation("slowLength");
		return s.length();
	}

	private int slowDouble(int i) {
		someLongComputation("slowDouble");
		return i * 2;
	}

	private void someLongComputation(String invoker) {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			System.out.println(invoker + " is working on " + i);
		}
	}

	private static class Promise<A> implements Future<A> {

		private final CompletableFuture<A> future;

		private Promise(CompletableFuture<A> future) {
			this.future = future;
		}

		public static final <A> Promise<A> promise(Supplier<A> supplier) {
			return new Promise<A>(CompletableFuture.supplyAsync(supplier));
		}

		public <B> Promise<B> map(Function<? super A, ? extends B> f) {
			return new Promise<B>(this.future.thenApplyAsync(f));
		}

		public <B> Promise<B> flatMap(Function<? super A, Promise<B>> f) {
			return new Promise<B>(this.future.thenComposeAsync(a -> f.apply(a).future));
		}

		@Override
		public boolean cancel(boolean mayInterruptIfRunning) {
			return this.future.cancel(mayInterruptIfRunning);
		}

		@Override
		public boolean isCancelled() {
			return this.future.isCancelled();
		}

		@Override
		public boolean isDone() {
			return this.future.isDone();
		}

		@Override
		public A get() throws InterruptedException, ExecutionException {
			return this.future.get();
		}

		@Override
		public A get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
			return this.future.get(timeout, unit);
		}
	}

}
