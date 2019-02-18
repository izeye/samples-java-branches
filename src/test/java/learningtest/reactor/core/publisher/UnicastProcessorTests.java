package learningtest.reactor.core.publisher;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.UnicastProcessor;
import reactor.util.concurrent.Queues;

/**
 * Tests for {@link UnicastProcessor}.
 *
 * @author Johnny Lim
 */
public class UnicastProcessorTests {

	@Test
	public void testUnboundedQueue() {
		CountDownLatch latch = new CountDownLatch(1);

		Queue<String> queue = Queues.<String>unboundedMultiproducer().get();
		UnicastProcessor<String> processor = UnicastProcessor.create(queue);

		processor.log().subscribe(new Subscriber<String>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(String s) {
				System.out.println("Before latch: " + s);
				try {
					latch.await();
					System.out.println("After latch: " + s);
				}
				catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
					throw new RuntimeException(ex);
				}
			}

			@Override
			public void onError(Throwable t) {
			}

			@Override
			public void onComplete() {
			}

		});

		new Thread(() -> {
			while (true) {
				System.out.println("Queue size: " + queue.size());
				sleep(1000);
			}
		}).start();

		ExecutorService executorService = Executors.newFixedThreadPool(100);

//		int maxMessageCount = Integer.MAX_VALUE;
		int maxMessageCount = 5_000_000;
		for (int i = 0; i < maxMessageCount; i++) {
			final int id = i;
			executorService.submit(() -> processor.onNext("Message #" + id));
			if (i % 1_000 == 0) {
				sleep(1);
			}
		}

//		latch.countDown();

//		sleep(10000);
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}
	}

}
