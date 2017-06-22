package learningtest.javac;

/**
 * Tests for constant arithmetic.
 *
 * @author Johnny Lim
 */
public class ConstantArithmeticTests {

	private static int STATIC_FIELD = 60 * 60 * 24 * 1;

	private static boolean STATIC_BOOLEAN_FIELD = true && (false || false || (true && true));

	private static final int STATIC_FINAL_FIELD = 60 * 60 * 24 * 2;

	public static void main(String[] args) {
		System.out.println(STATIC_FIELD);
		System.out.println(STATIC_BOOLEAN_FIELD);
		System.out.println(STATIC_FINAL_FIELD);
	}

}
