package learningtest.java.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Double}.
 *
 * @author Johnny Lim
 */
public class DoubleTests {

	@Test
	public void testNaN() {
		double value = Double.NaN;
		assertThat(value).isNaN();
	}

}
