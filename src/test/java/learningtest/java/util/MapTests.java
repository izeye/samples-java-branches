package learningtest.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Map}.
 *
 * @author Johnny Lim
 */
public class MapTests {

	@Test
	public void mapToMapViaStream() {
		Map<Long, String> firstNameById = new HashMap<>();
		firstNameById.put(1L, "Johnny");
		firstNameById.put(2L, "John");

		Map<Long, Person> personById = firstNameById.entrySet()
				.stream()
				.collect(Collectors.toMap(entry -> entry.getKey(), entry -> new Person(entry.getValue())));

		assertThat(personById).hasSize(2);

		Person firstPerson = personById.get(1L);
		assertThat(firstPerson.getFirstName()).isEqualTo("Johnny");

		Person secondPerson = personById.get(2L);
		assertThat(secondPerson.getFirstName()).isEqualTo("John");
	}

	@Test
	public void castNullFromMapToSubclassShouldBeOkay() {
		Map<String, Person> personByFirstName = new HashMap<>();
		Employee employee = (Employee) personByFirstName.get("Johnny");
		assertThat(employee).isNull();
	}

	private static class Person {

		private final String firstName;

		public Person(String firstName) {
			this.firstName = firstName;
		}

		public String getFirstName() {
			return this.firstName;
		}
	}

	private static class Employee extends Person {

		private final String id;

		public Employee(String id, String firstName) {
			super(firstName);
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

	}

}
