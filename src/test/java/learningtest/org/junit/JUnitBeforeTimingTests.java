package learningtest.org.junit;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for JUnit {@link Before} timing.
 *
 * @author Johnny Lim
 */
public class JUnitBeforeTimingTests {

	private String value;

	@Before
	public void setUp() {
		this.value = getValue();
	}

	private String getValue() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ex);
		}
		return "test";
	}

	@Test
	public void test() {
		assertThat(this.value).isEqualTo("test");
	}

}
