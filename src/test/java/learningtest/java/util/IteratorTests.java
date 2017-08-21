package learningtest.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Iterator}.
 *
 * @author Jungmuk Lim
 */
public class IteratorTests {

	@Test
	public void testForEachRemaining() {
		Iterator<String> iterator = Arrays.asList("Johnny", "John").iterator();

		List<String> names = new ArrayList<>();
		iterator.forEachRemaining(names::add);

		assertThat(names).containsExactly("Johnny", "John");
	}

}
