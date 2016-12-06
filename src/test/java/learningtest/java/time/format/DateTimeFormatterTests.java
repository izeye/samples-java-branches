package learningtest.java.time.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * Tests for {@link DateTimeFormatter}.
 *
 * @author Johnny Lim
 */
public class DateTimeFormatterTests {

	@Test
	public void testFormat() {
		LocalDateTime dateTime = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String formatted = formatter.format(dateTime);
		System.out.println(formatted);
	}

}
