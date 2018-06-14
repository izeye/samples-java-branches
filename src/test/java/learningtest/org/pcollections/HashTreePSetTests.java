package learningtest.org.pcollections;

import org.junit.Test;
import org.pcollections.HashTreePSet;
import org.pcollections.MapPSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link HashTreePSet}.
 *
 * @author Johnny Lim
 */
public class HashTreePSetTests {

	@Test
	public void test() {
		MapPSet<String> set = HashTreePSet.empty();
		MapPSet<String> somethingAdded = set.plus("something");
		MapPSet<String> somethingElseAdded = somethingAdded.plus("something else");

		assertThat(set).isEmpty();
		assertThat(somethingAdded).containsExactly("something");

		// This doesn't work. Why?
//		assertThat(somethingElseAdded).containsExactly("something", "something else");
		assertThat(somethingElseAdded)
				.hasSize(2)
				.contains("something", "something else");
	}

}
