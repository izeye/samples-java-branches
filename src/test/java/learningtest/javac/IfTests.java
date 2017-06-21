package learningtest.javac;

/**
 * Tests for {@code if}.
 *
 * @author Johnny Lim
 */
public class IfTests {

	public static void main(String[] args) {
		boolean booleanExpression = Boolean.parseBoolean(args[0]);
		
		if (!booleanExpression) {
			doSomething(1);
		}
		else {
			doSomething(0);
		}

		if (booleanExpression) {
			doSomething(0);
		}
		else {
			doSomething(1);
		}
	}

	public static void doSomething(int value) {
	}

}
