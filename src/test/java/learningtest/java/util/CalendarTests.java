package learningtest.java.util;

import org.junit.Test;

import java.util.Calendar;

/**
 * Tests for {@link Calendar}.
 *
 * @author Johnny Lim
 */
public class CalendarTests {

	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DATE, 1);
		System.out.println(calendar.getTime());

		calendar.add(Calendar.DATE, -1);
		System.out.println(calendar.getTime());
	}

}
