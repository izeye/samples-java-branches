package learningtest.java.lang;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Object}.
 *
 * @author Johnny Lim
 */
class ObjectTests {

	@Test
	void testToString() {
		// When debugging, you will see the side effect although there's no code to invoke the 'toString()'.
		// In fact, the IDE did!
		ClassHavingSideEffectInToString instance = new ClassHavingSideEffectInToString();
		instance.sayHello();
	}

	@Test
	void testHashCode() {
		System.out.println(new Object().hashCode());
		System.out.println(new Object().hashCode());
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
