package learningtest.reactor.core.publisher;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
//import reactor.core.scheduler.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Flux}.
 *
 * They are coming from https://www.baeldung.com/reactor-core.
 *
 * @author Johnny
 */
public class FluxTests {

	@Test
	public void subscribe() {
		List<Integer> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
				.log()
				.subscribe(elements::add);

		assertThat(elements).containsExactly(1, 2, 3, 4);
	}

	@Test
	public void subscribeWithSubscriber() {
		List<Integer> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
				.log()
				.subscribe(new Subscriber<Integer>() {

					@Override
					public void onSubscribe(Subscription s) {
						s.request(Long.MAX_VALUE);
					}

					@Override
					public void onNext(Integer integer) {
						elements.add(integer);
					}

					@Override
					public void onError(Throwable t) {
					}

					@Override
					public void onComplete() {
					}

				});

		assertThat(elements).containsExactly(1, 2, 3, 4);
	}

	@Test
	public void backpressure() {
		List<Integer> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
				.log()
				.subscribe(new Subscriber<Integer>() {

					private Subscription s;
					private int onNextAmount = 0;

					@Override
					public void onSubscribe(Subscription s) {
						this.s = s;
						s.request(2);
					}

					@Override
					public void onNext(Integer integer) {
						elements.add(integer);
						onNextAmount++;
						if (onNextAmount % 2 == 0) {
							s.request(2);
						}
					}

					@Override
					public void onError(Throwable t) {
					}

					@Override
					public void onComplete() {
					}

				});

		assertThat(elements).containsExactly(1, 2, 3, 4);
	}

	@Test
	public void map() {
		List<Integer> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
				.log()
				.map(i -> i * 2)
				.subscribe(elements::add);

		assertThat(elements).containsExactly(2, 4, 6, 8);
	}

	@Test
	public void zipWith() {
		List<String> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
				.log()
				.map(i -> i * 2)
				.zipWith(Flux.range(0, Integer.MAX_VALUE), (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
				.subscribe(elements::add);

		assertThat(elements).containsExactly(
				"First Flux: 2, Second Flux: 0",
				"First Flux: 4, Second Flux: 1",
				"First Flux: 6, Second Flux: 2",
				"First Flux: 8, Second Flux: 3");
	}

	@Test
	public void publish() {
		ConnectableFlux<Object> flux = Flux.create(fluxSink -> {
			long previousMillis = System.currentTimeMillis();
			long endMillis = previousMillis + TimeUnit.SECONDS.toMillis(5);
			while (previousMillis < endMillis) {
				long currentMillis = System.currentTimeMillis();
				if (currentMillis == previousMillis) {
					continue;
				}
				fluxSink.next(previousMillis);
				previousMillis = currentMillis;
			}
		})
				.log()
				.publish();

		flux.subscribe(System.out::println);
		flux.subscribe(System.out::println);
		flux.connect();
	}

	@Test
	public void sample() {
		ConnectableFlux<Object> flux = Flux.create(fluxSink -> {
			long previousMillis = System.currentTimeMillis();
			long endMillis = previousMillis + TimeUnit.SECONDS.toMillis(5);
			while (previousMillis < endMillis) {
				long currentMillis = System.currentTimeMillis();
				if (currentMillis == previousMillis) {
					continue;
				}
				fluxSink.next(previousMillis);
				previousMillis = currentMillis;
			}
		})
//				.log()
				.sample(Duration.ofSeconds(2))
				.log()
				.publish();

		flux.subscribe(System.out::println);
		flux.subscribe(System.out::println);
		flux.connect();
	}
	@Test
	public void subscribeOn() {
		List<Integer> elements = new CopyOnWriteArrayList<>();

		Flux.just(1, 2, 3, 4)
				.log()
				.map(i -> i * 2)
				// NOTE: This makes this test flaky with Gradle (okay with IntelliJ)
				// and the assertion failure seems quite confusing as follows:
				//
				//	Actual and expected should have same size but actual size was:
				//  <0>
				//			while expected size was:
				//  <4>
				//	Actual was:
				//  <[2, 4, 6, 8]>
				//	Expected was:
				//  <[2, 4, 6, 8]>
//				.subscribeOn(Schedulers.parallel())
				.subscribe(elements::add);

		assertThat(elements).containsExactly(2, 4, 6, 8);
	}

}
