package learningtest.expression;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@code boolean} short-circuiting.
 *
 * @author Johnny Lim
 */
class BooleanShortCircuitTests {

	@Test
	void doubleBarsShortCircuit() {
		System.out.println(returnTrue() || returnTrue());
	}

	@Test
	void singleBarDoesNotShortCircuit() {
		System.out.println(returnTrue() | returnTrue());
	}

	private boolean returnTrue() {
		System.out.println("Return true.");
		return true;
	}

}
