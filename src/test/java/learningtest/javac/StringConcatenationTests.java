package learningtest.javac;

/**
 * Tests for {@link String} concatenation.
 *
 * @author Johnny Lim
 */
public class StringConcatenationTests {

	public static void main(String[] args) {
		String string1 = "string 1";
		String string2 = "string 2";

		String concatenated = string1 + " : " + string2;
		System.out.println(concatenated);

		String anotherConcatenated = string1 + " : " + string2;
		anotherConcatenated += " 123";
		System.out.println(anotherConcatenated);
	}

}
