package learningtest.com.fasterxml.jackson.dataformat.smile;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.smile.SmileGenerator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Tests for {@link SmileGenerator}.
 *
 * @author Johnny Lim
 */
public class SmileGeneratorTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testWriteStringWithBrokenString() throws IOException {
		char[] chars = new char[] { '\ud937', '\u004f'};
		String brokenString = new String(chars);

		SmileFactory smileFactory = new SmileFactory();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		SmileGenerator smileGenerator = smileFactory.createGenerator(baos);

		this.thrown.expect(JsonGenerationException.class);
		this.thrown.expectMessage(
				"Invalid surrogate pair, starts with valid high surrogate (0xD937) but ends with invalid low surrogate (0x004F), not in valid range [0xDC00, 0xDFFF]");
		smileGenerator.writeString(brokenString);
	}

}
