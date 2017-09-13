package learningtest.com.google.common.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link BloomFilter}.
 *
 * @author Johnny Lim
 */
public class BloomFilterTests {

	@Test
	public void test() {
		BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 500, 0.01);

		filter.put(1);
		filter.put(2);
		filter.put(3);

		assertThat(filter.mightContain(1)).isTrue();
		assertThat(filter.mightContain(2)).isTrue();
		assertThat(filter.mightContain(3)).isTrue();

		assertThat(filter.mightContain(100)).isFalse();
	}

	@Test
	public void testTooManyElements() {
		BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 5, 0.01);

		IntStream.range(0, 100_000).forEach(filter::put);

		assertThat(filter.mightContain(1)).isTrue();
		assertThat(filter.mightContain(2)).isTrue();
		assertThat(filter.mightContain(3)).isTrue();

		assertThat(filter.mightContain(1_000_000)).isTrue();
	}

}
