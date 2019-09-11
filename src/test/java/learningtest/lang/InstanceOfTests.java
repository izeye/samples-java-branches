package learningtest.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@code instanceof}.
 *
 * @author Johnny Lim
 */
public class InstanceOfTests {

	@Test
	public void instanceOfWhenNullShouldReturnFalse() {
		String string = null;
		assertThat(string instanceof String).isFalse();
	}

}
