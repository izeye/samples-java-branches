package learningtest.org.junit.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link ExpectedException}.
 *
 * @author Johnny Lim
 */
public class ExpectedExceptionTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void throwsNothing() {
	}

	@Test
	public void throwsExceptionWithSpecificType() {
		this.thrown.expect(NullPointerException.class);
		throw new NullPointerException();
	}

}
