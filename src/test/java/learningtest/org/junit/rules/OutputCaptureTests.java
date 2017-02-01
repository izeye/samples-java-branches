package learningtest.org.junit.rules;

import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link OutputCapture}.
 *
 * @author Johnny Lim
 */
public class OutputCaptureTests {

	@Rule
	public OutputCapture capture = new OutputCapture();

	@Test
	public void test() {
		System.out.println("Hello, world!");
		System.err.println("Bye, world!");
		assertThat(capture.toString()).isEqualTo("Hello, world!\nBye, world!\n");
	}

}
