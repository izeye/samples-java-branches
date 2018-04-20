package learningtest.java.util.concurrent;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CountDownLatch}.
 *
 * @author Johnny Lim
 */
public class CountDownLatchTests {

	@Test
	public void test() {
		CountDownLatch latch = new CountDownLatch(1);
		latch.countDown();
		assertThat(latch.getCount()).isEqualTo(0);
		latch.countDown();
		assertThat(latch.getCount()).isEqualTo(0);
	}

}
