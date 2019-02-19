package learningtest.lang;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@code for} loop.
 *
 * @author Johnny Lim
 */
public class ForTests {

	@Test
	public void terminationExpressionWillBeEvaluatedOnEveryIteration() {
		AtomicInteger counter = new AtomicInteger(0);
		for (int i = 0; i < getEndIndex(counter); i++) {
			System.out.println(i + ": " + counter.get());
			assertThat(counter.get()).isEqualTo(i + 1);
		}
		assertThat(counter.get()).isEqualTo(11);
	}

	private int getEndIndex(AtomicInteger counter) {
		counter.incrementAndGet();
		return 10;
	}

}
