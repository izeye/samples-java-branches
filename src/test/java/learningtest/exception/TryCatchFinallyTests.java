package learningtest.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@code try}, {@code catch}, and {@code finally}.
 *
 * @author Johnny Lim
 */
public class TryCatchFinallyTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testFinallyWinsCatchWithThrowingException() {
		this.thrown.expectMessage("I'm from 'finally'.");
		testTryCatchFinally();
	}

	private void testTryCatchFinally() {
		try {
			System.out.println("I'm in 'try'.");
			throw new RuntimeException("I'm from 'try'.");
		}
		catch (Throwable ex) {
			System.out.println("I'm in 'catch'.");
			throw new RuntimeException("I'm from 'catch'.");
		}
		finally {
			System.out.println("I'm in 'finally'.");
			throw new RuntimeException("I'm from 'finally'.");
		}
	}

}
