package learningtest.java.lang;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 8. 23..
 */
public class EnumTests {

	@Test
	public void testOrdinal() {
		assertThat(Fruit.APPLE.ordinal(), is(0));
		assertThat(Fruit.BANANA.ordinal(), is(1));
	}
	
	enum Fruit {
		APPLE, BANANA
	}
	
}
