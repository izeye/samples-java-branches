package learningtest.org.junit.jupiter.api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;

/**
 * Tests for {@link Tag}.
 *
 * @author Johnny Lim
 */
@Tag("failing-tag")
public class TagTests {

	@Test
	void test() {
		fail();
	}

}
