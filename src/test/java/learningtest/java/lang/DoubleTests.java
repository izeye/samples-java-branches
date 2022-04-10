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

}
