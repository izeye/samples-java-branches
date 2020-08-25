package learningtest.reactor.core.publisher;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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

	@Test
	void delayElementWithMultipleMonos() {
		Set<String> set1 = new HashSet<>(Arrays.asList("apple"));
		Set<String> set2 = new HashSet<>(Arrays.asList("banana"));
		Mono<Set<String>> mono1 = Mono.just(set1).delayElement(Duration.ofSeconds(1));
		Mono<Set<String>> mono2 = Mono.just(set2).delayElement(Duration.ofSeconds(1));
		long startTimeMillis = System.currentTimeMillis();
		assertThat(mono1.block()).isEqualTo(set1);
		assertThat(mono2.block()).isEqualTo(set2);
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		assertThat(TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis)).isGreaterThanOrEqualTo(2);
	}

}
