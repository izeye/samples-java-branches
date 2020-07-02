package learningtest.java.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link Map}.
 *
 * @author Johnny Lim
 */
class MapTests {

	@Test
	void mapToMapViaStream() {
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
	void castNullFromMapToSubclassShouldBeOkay() {
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

	@Test
	void of() {
		assertThat(Map.of("a", "1", "b", "2", "c", "3")).containsOnlyKeys("a", "b", "c");
	}

	@Test
	void ofEntries() {
		assertThat(Map.ofEntries(entry("a", "1"), entry("b", "2"), entry("c", "3")))
				.containsOnlyKeys("a", "b", "c");
	}

	@Test
	void ofEntriesWithDuplicateKey() {
		assertThatIllegalArgumentException()
				.isThrownBy(() -> Map.ofEntries(entry("a", "1"), entry("b", "2"), entry("a", "3")));
	}

	@Test
	void entrySetValue() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");

		for (Map.Entry<String, String> entry : map.entrySet()) {
			entry.setValue("test");
		}

		assertThat(map).containsOnly(entry("a", "test"), entry("b", "test"), entry("c", "test"));
	}

	@Test
	void keySetRemove() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");

		Set<String> keySet = map.keySet();
		keySet.remove("b");
		assertThat(map).containsOnly(entry("a", "1"), entry("c", "3"));
	}

	@Test
	void keySetAdd() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");

		Set<String> keySet = map.keySet();
		assertThatThrownBy(() -> keySet.add("d"))
				.isExactlyInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	void testGetSecondKeysFromValueIntersection() {
		var mapA = Map.of(1, "a", 2, "b", 3, "c");
		var mapB = Map.of(5, "a", 6, "d", 7, "c");
		assertThat(getSecondKeysFromValueIntersection(mapA, mapB)).containsExactly(5, 7);
	}

	private Set<Integer> getSecondKeysFromValueIntersection(Map<Integer, String> first, Map<Integer, String> second) {
		var firstValues = new HashSet<>(first.values());
		var secondCopied = new HashMap<>(second);
		secondCopied.values().retainAll(firstValues);
		return secondCopied.keySet();
	}

}
