package learningtest.javac;

/**
 * Tests for complex {@code if} conditions.
 *
 * @author Johnny Lim
 */
public class IfComplexTests {

	public static void main(String[] args) {
		boolean a = Boolean.parseBoolean(args[0]);
		boolean b = Boolean.parseBoolean(args[1]);

		if (!a || !b) {
			doSomething();
		}

		if (!(a && b)) {
			doSomething();
		}
	}

	public static void doSomething() {
	}

}
