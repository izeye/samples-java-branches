package com.izeye.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link TimingUtils}.
 *
 * @author Johnny Lim
 */
class TimingUtilsTests {

	@Test
	@SuppressWarnings("unchecked")
	void timeWithSupplier() {
		Supplier<String> supplier = () -> "test";
		Consumer<Long> elapsedTimeConsumer = mock(Consumer.class);
		Consumer<String> resultConsumer = mock(Consumer.class);

		assertThat(TimingUtils.time(supplier, elapsedTimeConsumer, resultConsumer)).isEqualTo("test");
		verify(elapsedTimeConsumer).accept(any(Long.class));
		verify(resultConsumer).accept("test");
	}

	@Test
	void timeWithRunnable() {
		Runnable runnable = mock(Runnable.class);
		@SuppressWarnings("unchecked")
		Consumer<Long> elapsedTimeConsumer = mock(Consumer.class);

		TimingUtils.time(runnable, elapsedTimeConsumer);
		verify(elapsedTimeConsumer).accept(any(Long.class));
	}

}
