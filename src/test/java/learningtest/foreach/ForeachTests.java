package learningtest.foreach;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for for-each.
 *
 * @author Johnny Lim
 */
public class ForeachTests {

	@Test
	public void test() {
		AtomicInteger counter = new AtomicInteger(0);
		for (Integer number : getNumbers(counter)) {
			System.out.println(number);
		}
		assertThat(counter.get()).isEqualTo(1);
	}

	private static List<Integer> getNumbers(AtomicInteger counter) {
		counter.incrementAndGet();
		return Arrays.asList(1, 2, 3, 4, 5);
	}

}
