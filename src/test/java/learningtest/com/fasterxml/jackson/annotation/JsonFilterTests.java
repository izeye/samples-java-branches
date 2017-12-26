package learningtest.com.fasterxml.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JsonFilter}.
 *
 * @author Johnny Lim
 */
public class JsonFilterTests {

	@Test
	public void test() throws JsonProcessingException {
		SimpleBeanPropertyFilter filter = new SimpleBeanPropertyFilter() {
			@Override public void serializeAsField(Object pojo, JsonGenerator jgen,
					SerializerProvider provider, PropertyWriter writer) throws Exception {
				Person person = (Person) pojo;

				String firstName = person.getFirstName();
				char firstChar = firstName.charAt(0);
				if (!Character.isUpperCase(firstChar)) {
					person.setFirstName(
							Character.toUpperCase(firstChar) + firstName.substring(1));
				}

				super.serializeAsField(pojo, jgen, provider, writer);
			}
		};

		ObjectMapper objectMapper = new ObjectMapper();

		ObjectWriter objectWriter = objectMapper.writer(
				new SimpleFilterProvider().addFilter("myFilter", filter));

		Person person = new Person();
		person.setFirstName("johnny");

		String json = objectWriter.writeValueAsString(person);
		assertThat(json).isEqualTo("{\"firstName\":\"Johnny\"}");
	}

	@Data
	@JsonFilter("myFilter")
	static class Person {

		private String firstName;

	}

}
