package learningtest.java.lang.management;

/**
 * Test application for {@link Thread#sleep}.
 *
 * @author Johnny Lim
 */
public class ThreadTestApplication {

	public static void main(String[] args) {
		try {
			Thread.sleep(20000);
		}
		catch (InterruptedException ex) {
			System.out.println(ex);
		}
	}

}
