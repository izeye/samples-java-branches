package learningtest.reactor.core.publisher;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

/**
 * Tests for {@link Flux}.
 *
 * @author Johnny Lim
 */
class FluxTests {

	@Test
	void interval() throws InterruptedException {
		Flux<Long> flux = Flux.interval(Duration.ofSeconds(1));
		flux.subscribe(System.out::println);
		TimeUnit.SECONDS.sleep(3);
	}

}
