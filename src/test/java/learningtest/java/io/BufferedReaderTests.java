package learningtest.java.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link BufferedReader}.
 *
 * @author Johnny Lim
 */
public class BufferedReaderTests {

	@Test
	public void testLines() {
		BufferedReader br = new BufferedReader(new StringReader(String.format("a%nb%nc")));
		List<String> lines = br.lines().collect(Collectors.toList());
		assertThat(lines).containsExactly("a", "b", "c");
	}

}
