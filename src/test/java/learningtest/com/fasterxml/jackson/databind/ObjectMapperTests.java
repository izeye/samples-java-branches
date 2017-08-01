package learningtest.com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjectMapper}.
 *
 * @author Johnny Lim
 */
public class ObjectMapperTests {

	@Test
	public void testWriteValue() throws IOException {
		StringWriter sw = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(sw, new Foo("test"));
		assertThat(sw.toString()).isEqualTo("{\"barBar\":\"test\"}");
	}

	@Test
	public void testWriteValueWithPropertyNamingStrategy() throws IOException {
		StringWriter sw = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(
				PropertyNamingStrategy.SNAKE_CASE);
		mapper.writeValue(sw, new Foo("test"));
		assertThat(sw.toString()).isEqualTo("{\"bar_bar\":\"test\"}");
	}
	
	@Test
	public void testMultipleJsonPropertySetters() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "{\"name\": \"Johnny\"}";
		Environment environment = mapper.readValue(json, Environment.class);
		assertThat(environment.getName()).isEqualTo("Johnny");

		json = "{\"test_name\": \"Johnny\"}";
		environment = mapper.readValue(json, Environment.class);
		assertThat(environment.getName()).isEqualTo("Johnny");

		json = "{\"production_name\": \"Johnny\"}";
		environment = mapper.readValue(json, Environment.class);
		assertThat(environment.getName()).isEqualTo("Johnny");
	}

	@Test
	public void testJsonIgnoreOnSetter() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		String json = "{\"firstName\": \"Johnny\", \"lastName\": \"Lim\"}";
		Person person = mapper.readValue(json, Person.class);
		System.out.println(person);
		assertThat(person.getFirstName()).isEqualTo("Johnny");
		assertThat(person.getLastName()).isNull();

		Person anotherPerson = new Person();
		anotherPerson.setFirstName("Johnny");
		anotherPerson.setLastName("Lim");
		System.out.println(anotherPerson);
		assertThat(anotherPerson.getFirstName()).isEqualTo("Johnny with @JsonIgnore");
		assertThat(anotherPerson.getLastName()).isEqualTo("Lim with @JsonIgnore");
		String anotherJson = mapper.writeValueAsString(anotherPerson);
		System.out.println(anotherJson);
		assertThat(anotherJson).isEqualTo("{\"firstName\":\"Johnny with @JsonIgnore\"}");
	}

	static class Foo {
		private final String barBar;

		Foo(String barBar) {
			this.barBar = barBar;
		}

		public String getBarBar() {
			return barBar;
		}
	}

	@Data
	static class Person {

		@JsonProperty
		private String firstName;
		private String lastName;

		@JsonIgnore
		public void setFirstName(String firstName) {
			this.firstName = firstName + " with @JsonIgnore";
		}

		@JsonIgnore
		public void setLastName(String lastName) {
			this.lastName = lastName + " with @JsonIgnore";
		}

	}
	
}
