package learningtest.com.fasterxml.jackson.dataformat.xml;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link XmlMapper}.
 *
 * @author Johnny Lim
 */
public class XmlMapperTests {

	private ObjectMapper xmlMapper;

	@Before
	public void setUp() {
		JacksonXmlModule xmlModule = new JacksonXmlModule();
//		xmlModule.setDefaultUseWrapper(false);
		this.xmlMapper = new XmlMapper(xmlModule);
	}

	@Test
	public void test() throws IOException {
		Person person = new Person();
		person.setId(1L);
		PersonName name = new PersonName();
		name.setFirstName("Johnny");
		name.setLastName("Lim");
		person.setName(name);
		person.setAge(20);
		List<String> fruits = Arrays.asList("apple", "banana");
		person.setFavoriteFruits(fruits);
		person.setCreatedTime(new Date());

		String xml = this.xmlMapper.writeValueAsString(person);
		System.out.println(xml);

		Person deserialized = this.xmlMapper.readValue(xml, Person.class);
		System.out.println(deserialized);
		assertThat(deserialized).isEqualTo(person);

		// NOTE:
		// Can't handle collections or arrays with `Map`.
		// Not sure it's limitation on `XmlMapper` or XML.
		@SuppressWarnings("unchecked")
		Map<String, Object> map = this.xmlMapper.readValue(xml, Map.class);
		System.out.println(map);
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
		private List<String> favoriteFruits;

		private Date createdTime;

	}

}
