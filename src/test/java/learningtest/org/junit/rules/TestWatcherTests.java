package learningtest.org.junit.rules;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.junit.Assert.fail;

/**
 * Fill me!
 */
public class TestWatcherTests {

	private static String watchedLog;

	@Rule
	public TestWatcher watcher = new TestWatcher() {

		@Override
		protected void failed(Throwable e, Description description) {
			watchedLog += description + " failed\n";
			System.out.println(watchedLog);
		}

		@Override
		protected void succeeded(Description description) {
			watchedLog += description + "succeeded\n";
			System.out.println(watchedLog);
		}

	};

	@Ignore
	@Test
	public void fails() {
		fail();
	}

	@Test
	public void succeeds() {
	}

}
