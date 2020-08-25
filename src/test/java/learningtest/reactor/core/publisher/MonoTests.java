package learningtest.reactor.core.publisher;

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

}
