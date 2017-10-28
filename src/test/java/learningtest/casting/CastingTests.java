package learningtest.casting;

import org.junit.Test;

/**
 * Tests for type casting.
 *
 * @author Johnny Lim
 */
public class CastingTests {

	@Test
	public void testMultipleTypeCasting() {
		Object object = new C();

		// When is this useful?
		((A & B) object).a();
		((A & B) object).b();
	}

	interface A {

		default void a() {
			System.out.println("a");
		}

	}

	interface B {

		default void b() {
			System.out.println("b");
		}

	}

	static class C implements A, B {

		public void c() {
			System.out.println("c");
		}

	}

}
