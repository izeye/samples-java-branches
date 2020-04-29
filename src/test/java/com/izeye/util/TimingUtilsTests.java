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
	void time() {
		Supplier<String> supplier = () -> "test";
		Consumer<Long> elapsedTimeConsumer = mock(Consumer.class);
		Consumer<String> resultConsumer = mock(Consumer.class);

		assertThat(TimingUtils.time(supplier, elapsedTimeConsumer, resultConsumer)).isEqualTo("test");
		verify(elapsedTimeConsumer).accept(any(Long.class));
		verify(resultConsumer).accept("test");
	}

}
