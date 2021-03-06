package learningtest.reactor.core.publisher;

import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
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
		Set<String> merged = new HashSet<>(mergedFlux.collectList().block());
		assertThat(merged).isEqualTo(all);
		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		assertThat(TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis)).isLessThan(2);
	}

	@Test
	void subscribeWithConsumer() {
		Flux<Integer> integers = Flux.range(1, 3);
		integers.subscribe((i) -> System.out.println(i));
	}

	@Test
	void subscribeWithConsumerAndErrorConsumer() {
		Flux<Integer> integers = Flux.range(1, 4).map((i) -> {
			if (i <= 3) {
				return i;
			}
			throw new RuntimeException("Got to 4.");
		});
		integers.subscribe(
				(i) -> System.out.println(i),
				(error) -> System.err.println("Error: " + error));
	}

	@Test
	void subscribeWithConsumerErrorConsumerAndCompleteConsumer() {
		Flux<Integer> integers = Flux.range(1, 4);
		integers.subscribe(
				(i) -> System.out.println(i),
				(error) -> System.err.println("Error: " + error),
				() -> System.out.println("Done"));
	}

	@Test
	void subscribeWithConsumerErrorConsumerCompleteConsumerAndSubscriptionConsumer() {
		Flux<Integer> integers = Flux.range(1, 4);
		integers.subscribe(
				(i) -> System.out.println(i),
				(error) -> System.err.println("Error: " + error),
				() -> System.out.println("Done"),
				(subscription) -> {
					System.out.println("Subscription: " + subscription);
					subscription.request(10);
				});
	}

	@Test
	void subscribeWithSubscriber() {
		SampleSubscriber<Integer> sampleSubscriber = new SampleSubscriber<>();
		Flux<Integer> integers = Flux.range(1, 4);
		integers.subscribe(sampleSubscriber);
	}

	private static class SampleSubscriber<T> extends BaseSubscriber<T> {

		@Override
		protected void hookOnSubscribe(Subscription subscription) {
			System.out.println("Subscribed");
			request(1);
		}

		@Override
		protected void hookOnNext(T value) {
			System.out.println(value);
			request(1);
		}

	}

	@Test
	void request() {
		Flux.range(1, 10)
				.doOnRequest((n) -> System.out.println("Request of " + n))
				.subscribe(new BaseSubscriber<>() {

					@Override
					protected void hookOnSubscribe(Subscription subscription) {
						request(5);
					}

					@Override
					protected void hookOnNext(Integer value) {
						System.out.println("Cancelling after having received " + value);
						cancel();
					}

				});
	}

	@Test
	void delayElementsAndThenMap() {
		long startTimeMillis = System.currentTimeMillis();
		System.out.println("Start time (ms): " + startTimeMillis);

		List<String> fruits = Arrays.asList("apple", "banana", "orange");
		Duration delayDuration = Duration.ofSeconds(1);
		List<String> processedFruits = Flux.fromIterable(fruits)
				.delayElements(delayDuration)
				.map((fruit) -> {
					long expectedDelayMillis = (fruits.indexOf(fruit) + 1) * delayDuration.toMillis();
					System.out.println("Expected delay (ms): " + expectedDelayMillis);

					long currentTimeMillis = System.currentTimeMillis();
					System.out.println(fruit + " time (ms): " + currentTimeMillis);

					assertThat(currentTimeMillis - startTimeMillis).isGreaterThanOrEqualTo(expectedDelayMillis);
					return "processed " + fruit;
				})
				.collectList()
				.block();
		assertThat(processedFruits)
				.containsExactly("processed apple", "processed banana", "processed orange");

		long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
		System.out.println("Elapsed time (ms): " + elapsedTimeMillis);
	}

	@Test
	void fromIterable() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyElementsOf(iterable);
	}

	@Test
	void flatMap() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.flatMap((fruit) -> Mono.just(fruit).log()).log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyElementsOf(iterable);
	}

	@Test
	void delayElements() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.delayElements(Duration.ofSeconds(1)).log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyElementsOf(iterable);
	}

	@Test
	void parallel() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.parallel().log()
				.runOn(Schedulers.boundedElastic()).log()
				.sequential().log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyInAnyOrderElementsOf(iterable);
	}

	@Test
	void parallelAndOrdered() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.parallel().log()
				.runOn(Schedulers.boundedElastic()).log()
				.ordered(Comparator.naturalOrder()).log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyInAnyOrderElementsOf(iterable);
	}

	@Test
	void delayElementsAndThenParallel() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.delayElements(Duration.ofSeconds(1)).log()
				.parallel().log()
				.runOn(Schedulers.boundedElastic()).log()
				.sequential().log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyInAnyOrderElementsOf(iterable);
	}

	@Test
	void delayElementsParallelAndThenFlatMap() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.delayElements(Duration.ofSeconds(1)).log()
				.parallel().log()
				.runOn(Schedulers.boundedElastic()).log()
				.flatMap((fruit) -> Mono.just(fruit).log()).log()
				.sequential().log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyInAnyOrderElementsOf(iterable);
	}

	@Test
	void delayElementsParallelFlatMapAndThenOrdered() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.delayElements(Duration.ofSeconds(1)).log()
				.parallel().log()
				.runOn(Schedulers.boundedElastic()).log()
				.flatMap((fruit) -> Mono.just(fruit).log()).log()
				.ordered(Comparator.naturalOrder()).log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyInAnyOrderElementsOf(iterable);
	}

	@Disabled
	@Test
	void delayElementsParallelFlatMapAndThenOrderedWhenFlatMapThrowsExceptionCauseHang() {
		Set<String> iterable = new LinkedHashSet<>(Arrays.asList("apple", "banana", "orange"));
		List<String> collected = Flux.fromIterable(iterable).log()
				.delayElements(Duration.ofSeconds(1)).log()
				.parallel().log()
				.runOn(Schedulers.boundedElastic()).log()
				.flatMap((fruit) -> {
					if (true) {
						throw new RuntimeException("Error.");
					}
					return Mono.just(fruit).log();
				}).log()
				.ordered(Comparator.naturalOrder()).log()
				.collectList().log()
				.block();
		assertThat(collected).containsExactlyInAnyOrderElementsOf(iterable);
	}

}
