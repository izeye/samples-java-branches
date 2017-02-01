package learningtest.java.math;

import java.math.BigDecimal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link BigDecimal}.
 *
 * @author Johnny Lim
 */
public class BigDecimalTests {

	@Test
	public void test() {
		BigDecimal bigDecimal = new BigDecimal("1");
		BigDecimal added = bigDecimal.add(new BigDecimal("2"));
		assertThat(added.toString()).isEqualTo("3");

		bigDecimal = new BigDecimal("1");
		added = bigDecimal.add(new BigDecimal("2.2"));
		assertThat(added.toString()).isEqualTo("3.2");

		bigDecimal = new BigDecimal("1.1");
		added = bigDecimal.add(new BigDecimal("2.2"));
		assertThat(added.toString()).isEqualTo("3.3");
	}

}