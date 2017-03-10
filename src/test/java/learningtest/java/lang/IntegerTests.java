package learningtest.java.lang;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Integer}.
 *
 * @author Johnny Lim
 */
public class IntegerTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

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

	@Test
	public void testTernaryOperatorReturnsNullForInt() {
		this.thrown.expect(NullPointerException.class);
		getInt(false);
	}

	private int getInt(boolean b) {
		return b ? 1 : null;
	}

}
