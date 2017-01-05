package learningtest.com.fasterxml.jackson.annotation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JsonAnyGetter}.
 *
 * @author Johnny Lim
 */
public class JsonAnyGetterTests {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void test() throws IOException {
		Person person = new Person(1L, "Johnny", "Lim");
		person.set("age", 20);
		String json = this.objectMapper.writeValueAsString(person);
		System.out.println(json);

		Integer age = JsonPath.parse(json).read("$.properties.age", Integer.class);
		assertThat(age).isEqualTo(20);

		Person deserialized = this.objectMapper.readValue(json, Person.class);
		System.out.println(deserialized);
		assertThat(deserialized).isEqualTo(person);
	}

	@Test
	public void testJsonAnyGetter() throws IOException {
		PersonWithJsonAnyGetter person = new PersonWithJsonAnyGetter(2L, "John", "Kim");
		person.set("age", 30);
		String json = this.objectMapper.writeValueAsString(person);
		System.out.println(json);

		Integer age = JsonPath.parse(json).read("$.age", Integer.class);
		assertThat(age).isEqualTo(30);

		PersonWithJsonAnyGetter deserialized =
				this.objectMapper.readValue(json, PersonWithJsonAnyGetter.class);
		System.out.println(deserialized);
		assertThat(deserialized).isEqualTo(person);
	}

	@Data
	private static class Person {

		private final long id;
		private final String firstName;
		private final String lastName;

		private final Map<String, Object> properties = new HashMap<>();

		@JsonCreator
		public Person(
				@JsonProperty("id") long id,
				@JsonProperty("firstName") String firstName,
				@JsonProperty("lastName") String lastName) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public void set(String name, Object value) {
			this.properties.put(name, value);
		}

	}

	@Data
	private static class PersonWithJsonAnyGetter {

		private final long id;
		private final String firstName;
		private final String lastName;

		private final Map<String, Object> properties = new HashMap<>();

		@JsonCreator
		public PersonWithJsonAnyGetter(
				@JsonProperty("id") long id,
				@JsonProperty("firstName") String firstName,
				@JsonProperty("lastName") String lastName) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@JsonAnyGetter
		public Map<String, Object> getProperties() {
			return this.properties;
		}

		@JsonAnySetter
		public void set(String name, Object value) {
			this.properties.put(name, value);
		}

	}

}
