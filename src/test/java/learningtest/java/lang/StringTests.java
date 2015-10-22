package learningtest.java.lang;

import org.junit.Test;

/**
 * Created by izeye on 15. 10. 22..
 */
public class StringTests {
	
	@Test
	public void testFormat() {
		Integer i = 7;
		System.out.println(String.format("Test Integer: %d", i));
		System.out.println(String.format("Test Integer: %s", i));
		
		i = null;
		System.out.println(String.format("Test Integer: %d", i));
		System.out.println(String.format("Test Integer: %s", i));
	}
	
}
