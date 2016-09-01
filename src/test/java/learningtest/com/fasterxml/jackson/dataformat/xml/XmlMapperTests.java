package learningtest.com.fasterxml.jackson.dataformat.xml;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link XmlMapper}.
 *
 * @author Johnny Lim
 */
public class XmlMapperTests {

	private ObjectMapper xmlMapper = new XmlMapper();

	@Test
	public void test() throws IOException {
		Person person = new Person();
		person.setId(1L);
		PersonName name = new PersonName();
		name.setFirstName("Johnny");
		name.setLastName("Lim");
		person.setName(name);
		person.setAge(20);
		person.setCreatedTime(new Date());

		String xml = this.xmlMapper.writeValueAsString(person);
		System.out.println(xml);

		Person deserialized = this.xmlMapper.readValue(xml, Person.class);
		System.out.println(deserialized);
		assertThat(deserialized).isEqualTo(person);
	}

	@Data
	static class PersonName {

		private String firstName;
		private String lastName;

	}

	@Data
	static class Person {

		private Long id;
		private PersonName name;
		private int age;

		private Date createdTime;

	}

}
