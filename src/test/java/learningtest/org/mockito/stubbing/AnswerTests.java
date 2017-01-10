package learningtest.org.mockito.stubbing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Data;
import org.junit.After;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link Answer}.
 *
 * @author Johnny Lim
 */
public class AnswerTests {

	private final ExecutorService executorService = Executors.newSingleThreadExecutor();

	private final SomeService someService = mock(SomeService.class);

	@After
	public void cleanUp() {
		this.executorService.shutdown();
	}

	@Test
	public void test() {
		Something something = new Something(1L);
		given(this.someService.getSomeBoolean(something)).willAnswer(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				return true;
			}
		});
		assertThat(this.someService.getSomeBoolean(something)).isTrue();
		assertThat(this.someService.getSomeBoolean(new Something(1L))).isTrue();
		assertThat(this.someService.getSomeBoolean(new Something(2L))).isFalse();
	}

	// Remove 'expected' to see how it fails.
	@Test(expected = ComparisonFailure.class)
	public void testConcurrency() {
		int count = 100;

		CountDownLatch latch = new CountDownLatch(count);
		List<Something> somethings = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Something something = new Something(i);
			somethings.add(something);

			given(this.someService.getSomeBoolean(something)).willAnswer(new Answer<Boolean>() {
				@Override
				public Boolean answer(InvocationOnMock invocation) throws Throwable {
					System.out.println("In answer(): " + invocation.getArguments()[0]);
					return true;
				}
			});

			// Make concurrency intentionally.
			submit(latch, something);
		}

		try {
			latch.await();
		}
		catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}

		for (Something something : somethings) {
			assertThat(this.someService.getSomeBoolean(something)).isTrue();
		}
	}

	@Test
	public void testAvoidConcurrency() {
		int count = 100;

		CountDownLatch latch = new CountDownLatch(count);
		List<Something> somethings = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Something something = new Something(i);
			somethings.add(something);

			given(this.someService.getSomeBoolean(something)).willAnswer(new Answer<Boolean>() {
				@Override
				public Boolean answer(InvocationOnMock invocation) throws Throwable {
					System.out.println("In answer(): " + invocation.getArguments()[0]);
					return true;
				}
			});
		}

		// Avoid concurrency.
		for (Something something : somethings) {
			submit(latch, something);
		}

		try {
			latch.await();
		}
		catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}

		for (Something something : somethings) {
			assertThat(this.someService.getSomeBoolean(something)).isTrue();
		}
	}

	private void submit(CountDownLatch latch, Something something) {
		this.executorService.submit(() -> {
			Boolean someBoolean = this.someService.getSomeBoolean(something);
			if (!someBoolean) {
				System.err.println("someService.getSomeBoolean() failed with " + something);
			}

			latch.countDown();
		});
	}

	@Data
	private static class Something {

		private final long id;

		public Something(long id) {
			this.id = id;
		}

	}

	private interface SomeService {

		Boolean getSomeBoolean(Something something);

	}

}
