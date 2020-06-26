package learningtest.java.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link List}.
 *
 * @author Jungmuk Lim
 */
class ListTests {

	@Test
	void testSubListIndexOutOfBounds() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);

		assertThatThrownBy(() -> numbers.subList(0, 10))
				.isExactlyInstanceOf(IndexOutOfBoundsException.class);
	}

	@Test
	void testStreamMap() {
		List<Number> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(Long.MAX_VALUE);

		List<Long> longs = numbers.stream().map(number -> number.longValue())
				.collect(Collectors.toList());

		assertThat(longs).hasSize(2);
		assertThat(longs.get(0)).isInstanceOf(Long.class);
		assertThat(longs.get(1)).isInstanceOf(Long.class);
	}

	@Test
	void abuseRawType() {
		List<Number> numbers = new ArrayList<>();
		numbers.add(Long.valueOf(1L));

		@SuppressWarnings("unchecked")
		List<Long> longs = (List) numbers;
		assertThat(longs.get(0)).isEqualTo(1L);
	}

	@Test
	void forEachUsesIterator() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);

		for (Integer number : numbers) {
			System.out.println(number);
		}
	}

}
