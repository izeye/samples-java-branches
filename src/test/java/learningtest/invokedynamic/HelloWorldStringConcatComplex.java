package learningtest.invokedynamic;

/**
 * Demo class for {@code invokedynamic} for {@code String} concatenation with a loop in
 * Java 9+.
 *
 * @author Johnny Lim
 */
public class HelloWorldStringConcatComplex {

	public static void main(String[] args) {
		String message = "Hello";
		for (int i = 0; i < 25; i++) {
			message += i;
		}
		System.out.println(message);
	}

}
