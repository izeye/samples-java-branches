package learningtest.generics;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for generics callee parameter type.
 *
 * @author Johnny Lim
 */
public class GenericsCalleeParameterTypeTests {

	private final Object[] objects = {1, "test"};

	@Test
	public void test() {
		testFirst(get(0));
		testSecond(get(1));
	}

	private void testFirst(Integer value) {
		assertThat(value).isEqualTo(1);
	}

	private void testSecond(String value) {
		assertThat(value).isEqualTo("test");
	}

	@SuppressWarnings("unchecked")
	private <T> T get(int index) {
		return (T) this.objects[index];
	}

}
