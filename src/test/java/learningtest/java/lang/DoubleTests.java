package learningtest.java.lang;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Double}.
 *
 * @author Johnny Lim
 */
class DoubleTests {

	@Test
	void testNaN() {
		double value = Double.NaN;
		assertThat(value).isNaN();
	}

	@Test
	void equalityOperatorWithObjectsFromValueOf() {
		assertThat(Double.valueOf(1) == Double.valueOf(1)).isFalse();
		assertThat(Double.valueOf(Double.MAX_VALUE) == Double.MAX_VALUE).isTrue();
		assertThat(Double.valueOf(Double.POSITIVE_INFINITY) == Double.POSITIVE_INFINITY).isTrue();
	}

	@Test
	void equalityOperatorWithObjectsFromConstructor() {
		assertThat(new Double(1) == new Double(1)).isFalse();
		assertThat(new Double(Double.MAX_VALUE) == Double.MAX_VALUE).isTrue();
		assertThat(new Double(Double.POSITIVE_INFINITY) == Double.POSITIVE_INFINITY).isTrue();
	}

}
