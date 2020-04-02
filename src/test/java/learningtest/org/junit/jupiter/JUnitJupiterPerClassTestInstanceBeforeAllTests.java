package learningtest.org.junit.jupiter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Tests for {@link BeforeAll} with per-class test instance.
 *
 * @author Johnny Lim
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JUnitJupiterPerClassTestInstanceBeforeAllTests {

	private int count;

	@BeforeAll
	void setUp() {
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
