package learningtest.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AtomicInteger}.
 *
 * @author Johnny Lim
 */
public class AtomicIntegerTests {

	@Test
	public void testIncrementAndGet() {
		AtomicInteger counter = new AtomicInteger(Integer.MAX_VALUE);
		assertThat(counter.incrementAndGet()).isEqualTo(Integer.MIN_VALUE);
		assertThat(counter.incrementAndGet()).isEqualTo(Integer.MIN_VALUE + 1);
	}

}
