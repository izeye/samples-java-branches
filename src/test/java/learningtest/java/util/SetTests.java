package learningtest.java.util;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Set}.
 *
 * @author Johnny Lim
 */
public class SetTests {

	@Test
	public void test() {
		Set<String> set = new HashSet<>();
		set.add("a");
		set.add("b");
		set.add("c");

		Set<String> subset = new HashSet<>();
		subset.add("a");
		subset.add("b");

		assertThat(set.containsAll(subset)).isTrue();
	}

}
