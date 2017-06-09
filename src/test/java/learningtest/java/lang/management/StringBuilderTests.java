package learningtest.java.lang.management;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link StringBuilder}.
 *
 * @author Jungmuk Lim
 */
public class StringBuilderTests {

	@Test
	public void testAppendNull() {
		Integer value = null;
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		assertThat(sb.toString()).isEqualTo("null");
	}

}
