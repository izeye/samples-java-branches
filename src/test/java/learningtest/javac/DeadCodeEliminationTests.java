package learningtest.javac;

/**
 * Tests for dead code elimination.
 *
 * @author Johnny Lim
 */
public class DeadCodeEliminationTests {

	public static void main(String[] args) {
		if (false) {
			System.out.println("Hello, world!");
		}
	}

}
