package learningtest.org.mockito;

import lombok.Data;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.exceptions.misusing.UnfinishedStubbingException;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link org.mockito.Mockito}.
 *
 * @author Johnny Lim
 */
public class MockitoTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test() {
		SomeService someService = mock(SomeService.class);

		Something something = new Something(1L);
		// Break intentionally to see any potential side effect.
		OngoingStubbing<Boolean> ongoingStubbing = when(someService.getBoolean(something));
		ongoingStubbing.thenReturn(true);

		assertThat(someService.getBoolean(something)).isTrue();
		assertThat(someService.getBoolean(new Something(1L))).isTrue();
		assertThat(someService.getBoolean(new Something(2L))).isFalse();
	}

	@Test
	public void testMockedToString() {
		SomeService someService = mock(SomeService.class);
		someService.toString();

		Something something = new Something(1L);
		// Break intentionally to see any potential side effect.
		OngoingStubbing<Boolean> ongoingStubbing = when(someService.getBoolean(something));
		ongoingStubbing.thenReturn(true);

		assertThat(someService.getBoolean(something)).isTrue();
		assertThat(someService.getBoolean(new Something(1L))).isTrue();
		assertThat(someService.getBoolean(new Something(2L))).isFalse();
	}

	@Test
	public void testNotMockedToString() {
		SomeService someService = mock(SomeService.class);

		Something something = new Something(1L);
		when(someService.getBoolean(something));
		this.thrown.expect(UnfinishedStubbingException.class);
		someService.toString();
	}

	@Test
	public void testNotMockedToStringInDifferentMockHavingSameInterface() {
		SomeService someService = mock(SomeService.class);
		SomeService someService2 = mock(SomeService.class);

		Something something = new Something(1L);
		when(someService.getBoolean(something));
		this.thrown.expect(UnfinishedStubbingException.class);
		someService2.toString();
	}

	@Test
	public void testNotMockedToStringInDifferentMockHavingDifferentInterface() {
		SomeService someService = mock(SomeService.class);
		AnotherService anotherService = mock(AnotherService.class);

		Something something = new Something(1L);
		when(someService.getBoolean(something));
		this.thrown.expect(UnfinishedStubbingException.class);
		anotherService.toString();
	}

	@Data
	private static class Something {

		private final long id;

		public Something(long id) {
			this.id = id;
		}

	}

	private interface SomeService {

		Boolean getBoolean(Something something);

	}

	private interface AnotherService {
	}

}
