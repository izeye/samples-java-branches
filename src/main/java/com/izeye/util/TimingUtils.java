package com.izeye.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Utilities for timing.
 *
 * @author Johnny Lim
 */
public final class TimingUtils {

	/**
	 * Print timing to the standard output.
	 *
	 * @param supplier supplier to time
	 * @param <T> result type from the supplier
	 * @return result from the supplier
	 */
	public static <T> T printTiming(Supplier<T> supplier) {
		return time(supplier, (elapsedTimeMillis) -> System.out.println("Elapsed time: " + elapsedTimeMillis));
	}

	/**
	 * Time and supply elapsed time in milliseconds.
	 *
	 * @param supplier supplier to time
	 * @param elapsedTimeConsumer consumer for elapsed time in milliseconds
	 * @param <T> result type from the supplier
	 * @return result from the supplier
	 */
	public static <T> T time(Supplier<T> supplier, Consumer<Long> elapsedTimeConsumer) {
		return time(supplier, elapsedTimeConsumer, null);
	}

	/**
	 * Time and supply elapsed time in milliseconds and result.
	 *
	 * @param supplier supplier to time
	 * @param elapsedTimeConsumer consumer for elapsed time in milliseconds
	 * @param resultConsumer consumer for result
	 * @param <T> result type from the supplier
	 * @return result from the supplier
	 */
	public static <T> T time(Supplier<T> supplier, Consumer<Long> elapsedTimeConsumer, Consumer<T> resultConsumer) {
		long startTimeMillis = System.currentTimeMillis();
		try {
			T result = supplier.get();
			if (resultConsumer != null) {
				resultConsumer.accept(result);
			}
			return result;
		}
		finally {
			elapsedTimeConsumer.accept(System.currentTimeMillis() - startTimeMillis);
		}
	}

	/**
	 * Time and supply elapsed time in milliseconds.
	 *
	 * @param runnable runnable to time
	 * @param elapsedTimeConsumer consumer for elapsed time in milliseconds
	 */
	public static void time(Runnable runnable, Consumer<Long> elapsedTimeConsumer) {
		long startTimeMillis = System.currentTimeMillis();
		try {
			runnable.run();
		}
		finally {
			elapsedTimeConsumer.accept(System.currentTimeMillis() - startTimeMillis);
		}
	}

	private TimingUtils() {
	}

}
