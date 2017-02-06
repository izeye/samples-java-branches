package operator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for operators like bitwise 'and' {@code &}.
 *
 * @author Johnny Lim
 */
public class OperatorTests {

	@Test
	public void testBitwiseAnd() {
		SomeClass someClass = mock(SomeClass.class);

		boolean result = false;
		result &= someClass.isOk();
		assertThat(result).isFalse();

		verify(someClass, times(1)).isOk();
	}

	@Test
	public void testLogicalAnd() {
		SomeClass someClass = mock(SomeClass.class);

		boolean result = false;
		result = result && someClass.isOk();
		assertThat(result).isFalse();

		verify(someClass, times(0)).isOk();
	}

	private interface SomeClass {

		boolean isOk();

	}

}
