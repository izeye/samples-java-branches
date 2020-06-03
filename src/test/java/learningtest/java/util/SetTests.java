package learningtest.java.util;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Set}.
 *
 * @author Johnny Lim
 */
class SetTests {

	@Test
	void testContainsAll() {
		Set<String> set = new HashSet<>();
		set.add("a");
		set.add("b");
		set.add("c");

		Set<String> subset = new HashSet<>();
		subset.add("a");
		subset.add("b");

		assertThat(set.containsAll(subset)).isTrue();
	}

	@Test
	void of() {
		assertThat(Set.of("a", "b", "c")).containsExactly("a", "b", "c");
	}

}
