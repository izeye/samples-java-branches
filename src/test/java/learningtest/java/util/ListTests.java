package learningtest.java.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link List}.
 *
 * @author Jungmuk Lim
 */
public class ListTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSubListIndexOutOfBounds() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);

		this.thrown.expect(IndexOutOfBoundsException.class);
		numbers.subList(0, 10);
	}

}
