package learningtest.java.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	public void testParseWithWrongFormat() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date parsed = formatter.parse("2017-07-18");
		System.out.println(parsed);

		// NOTE: Expected 'ParseException' but just returns a wrong value.
		assertThat(parsed).isEqualTo(formatter.parse("20161207"));
	}

}
