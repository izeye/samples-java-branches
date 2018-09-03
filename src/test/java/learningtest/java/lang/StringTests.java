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

	@Test
	public void testReplaceAllWithLineSeparators() {
		assertThat("a\rb\nc\r\nd".replaceAll("[\r\n]+", "")).isEqualTo("abcd");
	}

	@Test
	public void testReplace() {
		assertThat("a+b+c".replace("+", "%20")).isEqualTo("a%20b%20c");
		assertThat("a.b.c".replace(".", "_")).isEqualTo("a_b_c");
	}

	@Test
	public void replaceFirst() {
		String regex = "^https?://[^/]+/";
		assertThat("https://www.google.com/hello".replaceFirst(regex, "")).isEqualTo("hello");
		assertThat("/hello".replaceFirst(regex, "")).isEqualTo("/hello");
	}

	@Test
	public void testSubstring() {
		assertThat("Hello, world!".substring(0, 0)).isEmpty();
	}

	@Test
	public void split() {
		assertThat(" ".split(",")).containsExactly(" ");
		assertThat(",".split(",")).isEmpty();
		assertThat(", ".split(",")).containsExactly("", " ");
	}

	@Test
	public void splitWithLimit() {
		assertThat("".split(":", 2)).containsExactly("");
		assertThat(":".split(":", 2)).containsExactly("", "");
		assertThat("a:".split(":", 2)).containsExactly("a", "");
		assertThat(":b".split(":", 2)).containsExactly("", "b");
		assertThat("a:b".split(":", 2)).containsExactly("a", "b");
		assertThat("::".split(":", 2)).containsExactly("", ":");
		assertThat("a:b:c".split(":", 2)).containsExactly("a", "b:c");
	}
	
}
