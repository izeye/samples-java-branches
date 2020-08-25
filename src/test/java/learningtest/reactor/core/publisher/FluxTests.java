package learningtest.reactor.core.publisher;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	void merge() {
		Set<String> set1 = new HashSet<>(Arrays.asList("apple"));
		Set<String> set2 = new HashSet<>(Arrays.asList("banana"));
		Set<String> all = new HashSet<>();
		all.addAll(set1);
		all.addAll(set2);

		Mono<Set<String>> mono1 = Mono.just(set1).delayElement(Duration.ofSeconds(1));
		Mono<Set<String>> mono2 = Mono.just(set2).delayElement(Duration.ofSeconds(1));
		long startTimeMillis = System.currentTimeMillis();
		Flux<String> mergedFlux = Flux.merge(
				mono1.flatMapMany(Flux::fromIterable), mono2.flatMapMany(Flux::fromIterable));
		Set<String> merged = new HashSet(mergedFlux.collectList().block());
		assertThat(merged).isEqualTo(all);
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		assertThat(TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis)).isLessThan(2);
	}

}
