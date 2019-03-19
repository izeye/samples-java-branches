package learningtest.error;

import org.junit.Ignore;
import org.junit.Test;

public class StackOverflowErrorTests {

	@Ignore
	@Test
	public void test() {
		doSomething();
	}

	private static void doSomething() {
		doSomething();
	}

}
