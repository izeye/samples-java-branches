package learningtest.com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjectMapper} with {@link JsonProperty.Access#READ_ONLY}.
 *
 * See https://github.com/FasterXML/jackson-databind/issues/935
 *
 * @author Johnny Lim
 */
public class ObjectMapperReadOnlyTests {

	@Test
	public void testReadValue() throws IOException {
		String json = "{\"firstName\": \"Johnny\", \"lastName\": \"Lim\", \"age\": 20}";
		ObjectMapper mapper = new ObjectMapper();
		Person person = mapper.readValue(json, Person.class);
		assertThat(person.getFirstName()).isEqualTo("Johnny");
		assertThat(person.getLastName()).isEqualTo("Lim");
		assertThat(person.getAge()).isEqualTo(0);
	}

	@Data
	private static class Person {

		private String firstName;
		private String lastName;


		@JsonProperty(access = JsonProperty.Access.READ_ONLY)
		private int age;

	}

}
