package learningtest.java.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link List}.
 *
 * @author Jungmuk Lim
 */
public class ListTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSubListIndexOutOfBounds() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);

		this.thrown.expect(IndexOutOfBoundsException.class);
		numbers.subList(0, 10);
	}

	@Test
	public void testStreamMap() {
		List<Number> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(Long.MAX_VALUE);

		List<Long> longs = numbers.stream().map(number -> number.longValue())
				.collect(Collectors.toList());

		assertThat(longs).hasSize(2);
		assertThat(longs.get(0)).isInstanceOf(Long.class);
		assertThat(longs.get(1)).isInstanceOf(Long.class);
	}

}
