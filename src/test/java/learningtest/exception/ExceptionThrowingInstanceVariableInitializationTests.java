package learningtest.exception;

import org.junit.Test;

/**
 * Tests for instance variable initializing throwing an {@link Exception}.
 *
 * @author Johnny Lim
 */
public class ExceptionThrowingInstanceVariableInitializationTests {

	@Test
	public void test() throws Exception {
		new ExceptionThrowingInstanceVariableInitializationClass().getSomething();
	}

	private static class ExceptionThrowingInstanceVariableInitializationClass {

		private final Something something = new Something();

		public ExceptionThrowingInstanceVariableInitializationClass() throws Exception {
		}

		public Something getSomething() {
			return this.something;
		}
	}

	private static class Something {

		public Something() throws Exception {
		}

	}

}
