package learningtest.java.text;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * Tests for {@link SimpleDateFormat}.
 *
 * @author Johnny Lim
 */
public class SimpleDateFormatTests {

	@Test
	public void testFormat() {
		Date date = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String formatted = format.format(date);
		System.out.println(formatted);
	}

}
