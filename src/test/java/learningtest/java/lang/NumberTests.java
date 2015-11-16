package learningtest.java.lang;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by izeye on 15. 11. 16..
 */
public class NumberTests {
	
	@Test
	public void testLongAndPrimitiveInt() {
		Long l = 1L;
		int i = 1;
		
		assertFalse(l.equals(i));
		assertTrue(l == i);
		
	}
	
}
