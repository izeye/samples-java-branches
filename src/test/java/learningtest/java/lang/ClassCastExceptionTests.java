package learningtest.java.lang;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link ClassCastException}.
 *
 * @author Johnny Lim
 */
public class ClassCastExceptionTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test() {
		this.thrown.expect(ClassCastException.class);
		handle(new A());
	}

	private void handle(IA ia) {
		System.out.println((B) ia);
	}

	private interface IA {
	}

	private static class A implements IA {
	}

	private static class B extends A {
	}

}
