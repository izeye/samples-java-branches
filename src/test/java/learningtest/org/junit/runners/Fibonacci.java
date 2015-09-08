package learningtest.org.junit.runners;

/**
 * Created by izeye on 15. 9. 8..
 */
public class Fibonacci {
	
	public static int compute(int input) {
		switch (input) {
			case 0:
				return 0;
			
			case 1:
				return 1;
			
			default:
				return compute(input - 2) + compute(input - 1);
		}
	}
	
}
