package learningtest.java.util.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Collectors}.
 *
 * @author Johnny Lim
 */
public class CollectorsTests {

	@Test
	public void testJoining() {
		List<String> fruits = Arrays.asList("apple", "banana");
		String joined = fruits.stream().collect(Collectors.joining(", ", "[", "]"));
		assertThat(joined).isEqualTo("[apple, banana]");

		assertThat(fruits.stream().collect(Collectors.joining())).isEqualTo("applebanana");
	}

}
