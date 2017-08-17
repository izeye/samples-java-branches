package operator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@code instanceof}.
 *
 * @author Johnny Lim
 */
public class InstanceofTests {

	@Test
	public void testNull() {
		String string = null;
		assertThat(string instanceof String).isFalse();
	}

}
