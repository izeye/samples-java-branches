package learningtest.inheritance;

import org.junit.Test;

/**
 * Tests for {@code super()}.
 *
 * @author Johnny Lim
 */
public class SuperTests {

	@Test
	public void test() {
		new Child1();
		new Child2();
		new Child2("test");
		new Child3();
		new Child3("test");
	}

	private static class Parent {

		public Parent() {
			System.out.println("Parent() called.");
		}

		public Parent(String string) {
			System.out.println("Parent() called with '" + string + "'.");
		}

	}

	private static class Child1 extends Parent {
	}

	private static class Child2 extends Parent {

		public Child2() {
			System.out.println("Child2() called.");
		}

		public Child2(String string) {
			System.out.println("Child2() called with '" + string + "'.");
		}

	}

	private static class Child3 extends Parent {

		public Child3() {
			System.out.println("Child3() called.");
		}

		public Child3(String string) {
			super(string);
			System.out.println("Child3() called with '" + string + "'.");
		}

	}
	
}
