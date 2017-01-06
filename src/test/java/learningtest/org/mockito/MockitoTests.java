package learningtest.org.mockito;

import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link org.mockito.Mockito}.
 *
 * @author Johnny Lim
 */
public class MockitoTests {

	@Test
	public void test() {
		SomeService someService = mock(SomeService.class);

		Something something = new Something(1L);
		when(someService.getBoolean(something)).thenReturn(true);

		assertThat(someService.getBoolean(something)).isTrue();
		assertThat(someService.getBoolean(new Something(1L))).isTrue();
		assertThat(someService.getBoolean(new Something(2L))).isFalse();
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

}
