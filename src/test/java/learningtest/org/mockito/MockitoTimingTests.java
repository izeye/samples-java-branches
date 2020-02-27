package learningtest.org.mockito;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for Mockito timing.
 *
 * @author Johnny Lim
 */
public class MockitoTimingTests {

	@Test
	public void withMockito() {
		long startTimeMillis = System.currentTimeMillis();

		Person person = mock(Person.class);
		when(person.getFirstName()).thenReturn("Johnny");
		when(person.getLastName()).thenReturn("Lim");

		long elapsedTime = System.currentTimeMillis() - startTimeMillis;
		System.out.println("Elapsed time: " + elapsedTime);

		assertThat(person.getFirstName()).isEqualTo("Johnny");
		assertThat(person.getLastName()).isEqualTo("Lim");
	}

	@Test
	public void withoutMockito() {
		long startTimeMillis = System.currentTimeMillis();

		Person person = new DefaultPerson("Johnny", "Lim");

		long elapsedTime = System.currentTimeMillis() - startTimeMillis;
		System.out.println("Elapsed time: " + elapsedTime);

		assertThat(person.getFirstName()).isEqualTo("Johnny");
		assertThat(person.getLastName()).isEqualTo("Lim");
	}

	interface Person {

		String getFirstName();

		String getLastName();

	}

	static class DefaultPerson implements Person {

		private final String firstName;
		private final String lastName;

		DefaultPerson(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override
		public String getFirstName() {
			return this.firstName;
		}

		@Override
		public String getLastName() {
			return this.lastName;
		}

	}

}
