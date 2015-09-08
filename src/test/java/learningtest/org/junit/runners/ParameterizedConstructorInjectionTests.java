package learningtest.org.junit.runners;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by izeye on 15. 9. 8..
 */
@RunWith(Parameterized.class)
public class ParameterizedConstructorInjectionTests {
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
		});
	}
	
	private int input;
	private int expected;
	
	public ParameterizedConstructorInjectionTests(int input, int expected) {
		this.input = input;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		assertEquals(expected, Fibonacci.compute(input));
	}
	
}
