package learningtest.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 3. 21..
 */
public class EqualsAndHashCodeTests {
	
	@Test
	public void test() {
		long currentTimeMillis = System.currentTimeMillis();
		Person person1 = new Person("Johnny", "Lim", new Date(currentTimeMillis));
		Person person2 = new Person("Johnny", "Lim", new Timestamp(currentTimeMillis));
		Person person3 = new Person("Johnny", "Lee", new Timestamp(currentTimeMillis));
		
		assertThat(person1).isEqualTo(person2);
		assertThat(person1).isNotEqualTo(person3);
	}
	
	@Data
	@AllArgsConstructor
	@EqualsAndHashCode(exclude = "birthDate")
	static class Person {
		
		private String firstName;
		private String lastName;
		private Date birthDate;
		
	}
	
}
