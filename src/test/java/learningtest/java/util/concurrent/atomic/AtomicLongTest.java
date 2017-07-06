package learningtest.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AtomicLong}.
 *
 * @author Johnny Lim
 */
public class AtomicLongTest {

	@Test
	public void testIncrementAndGet() {
		AtomicLong counter = new AtomicLong(Long.MAX_VALUE);
		assertThat(counter.incrementAndGet()).isEqualTo(Long.MIN_VALUE);
		assertThat(counter.incrementAndGet()).isEqualTo(Long.MIN_VALUE + 1);
	}

}
