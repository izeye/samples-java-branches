package learningtest.java.lang;

import org.junit.Test;

/**
 * Tests for {@link Object}.
 */
public class ObjectTests {

	@Test
	public void testToString() {
		// When debugging, you will see the side effect although there's no code to invoke the 'toString()'.
		// In fact, the IDE did!
		ClassHavingSideEffectInToString instance = new ClassHavingSideEffectInToString();
		instance.sayHello();
	}

	private static class ClassHavingSideEffectInToString {

		public void sayHello() {
			System.out.println("Hello!");
		}

		@Override
		public String toString() {
			System.out.println("I'm the side effect!");

			return super.toString();
		}

	}

}
