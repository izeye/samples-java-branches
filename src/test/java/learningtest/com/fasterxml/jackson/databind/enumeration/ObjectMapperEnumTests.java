package learningtest.com.fasterxml.jackson.databind.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ObjectMapper} with {@code enum}.
 *
 * @author Johnny Lim
 */
public class ObjectMapperEnumTests {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void test() {
		Person person = new Person();
		person.setName("Johnny");
		person.setGrade(Grade.GOOD);
		try {
			String json = this.objectMapper.writeValueAsString(person);
			assertThat(json).isEqualTo("{\"name\":\"Johnny\",\"grade\":1}");
			
			Person deserialized = this.objectMapper.readValue(json, Person.class);
			assertThat(deserialized).isEqualTo(person);
		}
		catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Data
	static class Person {
		
		private String name;
		private Grade grade;
		
	}
	
	enum Grade {
		
		GOOD(1), SO_SO(0), BAD(-1);
		
		private static final Map<Integer, Grade> VALUE_BY_CODE;
		static {
			Map<Integer, Grade> valueByCode = new HashMap<>();
			for (Grade grade : values()) {
				valueByCode.put(grade.getCode(), grade);
			}
			VALUE_BY_CODE = Collections.unmodifiableMap(valueByCode);
		}

		private final int code;

		Grade(int code) {
			this.code = code;
		}
		
		@JsonCreator
		public static Grade fromCode(int code) {
			return VALUE_BY_CODE.get(code);
		}

		@JsonValue
		public int getCode() {
			return this.code;
		}
		
	}
	
}
