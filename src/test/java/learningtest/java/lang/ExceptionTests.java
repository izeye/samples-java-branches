package learningtest.java.lang;

import org.junit.Test;

/**
 * Tests for {@link Exception}.
 */
public class ExceptionTests {

	@Test
	public void testOverloading() {
		IllegalStateException illegalStateException = new IllegalStateException();

		ExceptionHandler exceptionHandler = new ExceptionHandler();
		exceptionHandler.handle(illegalStateException);

		Throwable throwable = illegalStateException;
		exceptionHandler.handle(throwable);

		try {
			throw illegalStateException;
		}
		catch (IllegalStateException ex) {
			exceptionHandler.handle(ex);
		}
		catch (Throwable ex) {
			exceptionHandler.handle(ex);
		}

		try {
			throw throwable;
		}
		catch (IllegalStateException ex) {
			exceptionHandler.handle(ex);
		}
		catch (Throwable ex) {
			exceptionHandler.handle(ex);
		}

		try {
			throw illegalStateException;
		}
		catch (IllegalStateException | IllegalArgumentException ex) {
			exceptionHandler.handle(ex);
		}
		catch (Throwable ex) {
			exceptionHandler.handle(ex);
		}

		if (throwable instanceof IllegalStateException) {
			exceptionHandler.handle((IllegalStateException) throwable);
		}
	}

	private class ExceptionHandler {

		public void handle(IllegalStateException ex) {
			System.out.println("Handle IllegalStateException: " + ex);
		}

		public void handle(Throwable ex) {
			System.out.println("Handle Throwable: " + ex);
		}

	}

}
