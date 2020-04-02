package learningtest.org.junit.jupiter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link BeforeAll}.
 *
 * @author Johnny Lim
 */
class JUnitJupiterBeforeAllTests {

	private static int count;

	@BeforeAll
	static void setUp() {
		count++;
		System.out.println("'count' in setUp(): " + count);
	}

	@Test
	void test1() {
		System.out.println("'count' in test1(): " + count);
	}

	@Test
	void test2() {
		System.out.println("'count' in test2(): " + count);
	}

}
