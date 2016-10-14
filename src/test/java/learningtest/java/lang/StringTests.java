package learningtest.java.lang;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link String}.
 *
 * @author Johnny Lim
 */
public class StringTests {
	
	@Test
	public void testFormat() {
		Integer i = 7;
		System.out.println(String.format("Test Integer: %d", i));
		System.out.println(String.format("Test Integer: %s", i));
		
		i = null;
		System.out.println(String.format("Test Integer: %d", i));
		System.out.println(String.format("Test Integer: %s", i));
	}

	@Test
	public void testValueOfBoolean() {
		assertThat(String.valueOf(true)).isEqualTo("true");
		assertThat(String.valueOf(false)).isEqualTo("false");
	}

	@Test
	public void testReplaceAll() {
		assertThat("a:b:c".replaceAll(":", "")).isEqualTo("abc");
	}
	
}
