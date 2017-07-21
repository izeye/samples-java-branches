package learningtest.java.util.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Tests for {@link ExecutorService} having daemon workers
 * with {@link Thread#sleep(long)}.
 *
 * @author Johnny Lim
 */
public class ExecutorServiceSleepDaemonTests {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10, new DaemonThreadFactory());
		executorService.submit(() -> {
			try {
				Thread.sleep(5000);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println(new Date() + ": sleep() completed.");
		});
		System.out.println(new Date() + ": end of code.");
	}

	private static class DaemonThreadFactory implements ThreadFactory {

		private ThreadFactory threadFactory = Executors.defaultThreadFactory();

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = this.threadFactory.newThread(r);
			thread.setDaemon(true);
			return thread;
		}

	}

}
