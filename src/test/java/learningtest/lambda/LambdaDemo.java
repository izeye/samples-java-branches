package learningtest.lambda;

/**
 * Demo for lambda.
 *
 * @author Johnny Lim
 */
public class LambdaDemo {

	public static void main(String[] args) {
		Runnable runnable = () -> System.out.println("Hello");
		runnable.run();
	}

}
