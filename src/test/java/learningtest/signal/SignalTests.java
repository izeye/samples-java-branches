package learningtest.signal;

public class SignalTests {

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override public void run() {
				System.out.println("Good bye!");
			}
		}));

		try {
			Thread.sleep(60000);
		}
		catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

}
