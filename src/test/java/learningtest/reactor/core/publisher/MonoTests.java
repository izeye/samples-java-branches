package learningtest.reactor.core.publisher;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Mono}.
 *
 * @author Johnny Lim
 */
class MonoTests {

	@Test
	void mapWithString() {
		String string = "Hello, world!";
		Mono<Integer> length = Mono.just(string).map((s) -> s.length());
		assertThat(length.block()).isEqualTo(string.length());
	}

	@Test
	void delayElement() {
		String string = "Hello";
		Mono<String> mono = Mono.just(string).delayElement(Duration.ofSeconds(1));
		long startTimeMillis = System.currentTimeMillis();
		assertThat(mono.block()).isEqualTo(string);
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		assertThat(TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis)).isGreaterThanOrEqualTo(1);
	}

}
