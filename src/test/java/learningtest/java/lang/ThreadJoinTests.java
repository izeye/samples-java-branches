package learningtest.java.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Thread#join()}.
 *
 * @author Johnny Lim
 */
class ThreadJoinTests {

	@Test
	void test() {
		List<String> targets = Arrays.asList("a", "b", "c");
		List<Thread> threads = new ArrayList<>();
		targets.forEach(target -> {
			Thread thread = new Thread(() -> doSomething(target));
			threads.add(thread);
			thread.start();
		});
		threads.forEach(thread -> {
			try {
				thread.join();
			}
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		});
	}

	private void doSomething(String target) {
		throw new RuntimeException("Failed to do something: " + target);
	}

}
