package com.izeye.util;

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
		long startTimeMillis = System.currentTimeMillis();
		try {
			return supplier.get();
		}
		finally {
			long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
			System.out.println("Elapsed time: " + elapsedTimeMillis);
		}
	}

	private TimingUtils() {
	}

}
