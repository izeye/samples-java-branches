package learningtest.java.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Integer}.
 *
 * @author Johnny Lim
 */
public class IntegerTests {

	@Test
	public void testToHexString() {
		byte b = 0;
		assertThat(Integer.toHexString(b)).isEqualTo("0");
		b = 0xf;
		assertThat(Integer.toHexString(b)).isEqualTo("f");
		b = (byte) 0xff;
		assertThat(Integer.toHexString(b)).isEqualTo("ffffffff");
		assertThat(Integer.toHexString(b & 0xff)).isEqualTo("ff");
	}

}
