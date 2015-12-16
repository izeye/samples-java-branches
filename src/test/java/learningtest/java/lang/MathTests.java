package learningtest.java.lang;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 12. 16..
 */
public class MathTests {
	
	@Test
	public void testAbs() {
		// `abs()` doesn't work for `Long.MIN_VALUE`.
		assertThat(Math.abs(Long.MIN_VALUE), is(Long.MIN_VALUE));
	}
	
}
