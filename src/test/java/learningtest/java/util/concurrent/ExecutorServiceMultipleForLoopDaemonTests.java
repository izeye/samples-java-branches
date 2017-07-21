package learningtest.java.util.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Tests for {@link ExecutorService} having daemon workers with multiple for-loops.
 *
 * @author Johnny Lim
 */
public class ExecutorServiceMultipleForLoopDaemonTests {

	public static void main(String[] args) {
		ExecutorService executorService = Executors
				.newFixedThreadPool(10, new DaemonThreadFactory());
		executorService.submit(() -> {
			double d = 0d;
			for (int i = 0; i < Integer.MAX_VALUE / 5; i++) {
				d += Math.sqrt(Integer.MAX_VALUE);
			}
			for (int i = 0; i < Integer.MAX_VALUE / 5; i++) {
				d += Math.sqrt(Integer.MAX_VALUE);
			}
			for (int i = 0; i < Integer.MAX_VALUE / 5; i++) {
				d += Math.sqrt(Integer.MAX_VALUE);
			}
			for (int i = 0; i < Integer.MAX_VALUE / 5; i++) {
				d += Math.sqrt(Integer.MAX_VALUE);
			}
			for (int i = 0; i < Integer.MAX_VALUE / 5; i++) {
				d += Math.sqrt(Integer.MAX_VALUE);
			}
			System.out.println(new Date() + ": " + d);
		});
		System.out.println(new Date() + ": end of code.");
	}

	private static class DaemonThreadFactory implements ThreadFactory {

		private ThreadFactory threadFactory = Executors.defaultThreadFactory();

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = threadFactory.newThread(r);
			thread.setDaemon(true);
			return thread;
		}

	}

}
