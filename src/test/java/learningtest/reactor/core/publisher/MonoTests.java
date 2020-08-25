package learningtest.reactor.core.publisher;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
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

	@Test
	void zip() {
		Set<String> stringSet1 = new HashSet<>(Arrays.asList("apple"));
		Set<String> stringSet2 = new HashSet<>(Arrays.asList("banana"));
		Set<String> stringAll = new HashSet<>();
		stringAll.addAll(stringSet1);
		stringAll.addAll(stringSet2);

		Set<Integer> integerSet1 = new HashSet<>(Arrays.asList(1));
		Set<Integer> integerSet2 = new HashSet<>(Arrays.asList(2));
		Set<Integer> integerAll = new HashSet<>();
		integerAll.addAll(integerSet1);
		integerAll.addAll(integerSet2);

		Mono<Set<String>> stringMono1 = Mono.just(stringSet1).delayElement(Duration.ofSeconds(1));
		Mono<Set<String>> stringMono2 = Mono.just(stringSet2).delayElement(Duration.ofSeconds(1));
		Mono<Set<Integer>> integerMono1 = Mono.just(integerSet1).delayElement(Duration.ofSeconds(1));
		Mono<Set<Integer>> integerMono2 = Mono.just(integerSet2).delayElement(Duration.ofSeconds(1));

		long startTimeMillis = System.currentTimeMillis();

		Flux<String> mergedStringFlux = Flux.merge(
				stringMono1.flatMapMany(Flux::fromIterable), stringMono2.flatMapMany(Flux::fromIterable));
		Mono<List<String>> listMono1 = mergedStringFlux.collectList();

		Flux<Integer> mergedIntegerFlux = Flux.merge(
				integerMono1.flatMapMany(Flux::fromIterable), integerMono2.flatMapMany(Flux::fromIterable));
		Mono<List<Integer>> listMono2 = mergedIntegerFlux.collectList();

		Mono<Zipped> zippedMono = Mono.zip(
				listMono1, listMono2, (mono1, mono2) -> new Zipped(new HashSet<>(mono1), new HashSet<>(mono2)));
		Zipped zipped = zippedMono.block();
		assertThat(zipped.getStringSet()).isEqualTo(stringAll);
		assertThat(zipped.getIntegerSet()).isEqualTo(integerAll);

		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		assertThat(TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis)).isLessThan(2);
	}

	@Data
	private static class Zipped {

		private final Set<String> stringSet;
		private final Set<Integer> integerSet;

	}

}
