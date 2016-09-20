package learningtest.java.util.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Stream}.
 *
 * @author Johnny Lim
 */
public class StreamTests {

	@Test
	public void testIterateAndLimit() {
		assertThat(Stream.iterate(1, i -> i + 1).limit(5)).containsExactly(1, 2, 3, 4, 5);
	}
	
	@Test
	public void test() {
		int n = 10;
		List<int[]> pairs = Stream.iterate(1, i -> i + 1).limit(n)
				.flatMap(i -> Stream.iterate(1, j -> j + 1).limit(i).map(j -> new int[]{i, j}))
				.filter(pair -> isPrime(pair[0] + pair[1]))
				.collect(toList());
		pairs.stream().forEach(pair -> System.out.println(Arrays.toString(pair)));
		assertThat(pairs).doesNotContain(new int[] {1, 1});
		assertThat(pairs).doesNotContain(new int[] {1, 2});
		assertThat(pairs).contains(new int[] {2, 1});
	}

	private boolean isPrime(int n) {
		return Stream.iterate(2, i -> i + 1).limit((long) Math.sqrt(n)).noneMatch(i -> n % i == 0);
	}
	
}
