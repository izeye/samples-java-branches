package learningtest.assertion;

/**
 * Tests for {@code assert}.
 *
 * @author Johnny Lim
 */
public class AssertTests {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			assert false;
		});
		thread.start();
		try {
			thread.join();
		}
		catch (InterruptedException ex) {
			System.out.println(ex);
		}

		System.out.println("'assert' failure in a non-main thread is okay.");

		try {
			assert false;
		}
		catch (Exception ex) {
			System.out.println(ex);
		}

		System.out.println("'assert' failure in the main thread is okay.");
	}

}
