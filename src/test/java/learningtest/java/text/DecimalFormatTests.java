package learningtest.java.text;

import java.text.DecimalFormat;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DecimalFormat}.
 *
 * @author Johnny Lim
 */
public class DecimalFormatTests {

	@Test
	public void testFormat() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###");

		assertThat(decimalFormat.format(100)).isEqualTo("100");
		assertThat(decimalFormat.format(1000)).isEqualTo("1,000");
		assertThat(decimalFormat.format(10000)).isEqualTo("10,000");
		assertThat(decimalFormat.format(100000)).isEqualTo("100,000");
		assertThat(decimalFormat.format(1000000)).isEqualTo("1,000,000");
	}

}
