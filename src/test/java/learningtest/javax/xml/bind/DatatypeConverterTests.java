package learningtest.javax.xml.bind;

import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DatatypeConverter}.
 *
 * @author Johnny Lim
 */
public class DatatypeConverterTests {

	@Test
	public void testPrintBase64Binary() {
		String message = "Hello, world";
		byte[] bytes = message.getBytes();

		String base64Encoded = DatatypeConverter.printBase64Binary(bytes);

		assertThat(base64Encoded)
				.isEqualTo(new String(Base64.getEncoder().encode(bytes)));
	}

}
