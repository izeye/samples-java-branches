package learningtest.com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 15. 8. 31..
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

	static class Foo {
		private final String barBar;

		Foo(String barBar) {
			this.barBar = barBar;
		}

		public String getBarBar() {
			return barBar;
		}
	}
	
}
