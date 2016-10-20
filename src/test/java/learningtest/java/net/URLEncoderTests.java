package learningtest.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link URLEncoder}.
 *
 * @author Johnny Lim
 */
public class URLEncoderTests {

	@Test
	public void testEncode() throws UnsupportedEncodingException {
		assertThat(URLEncoder.encode("가 나", "UTF-8")).isEqualTo("%EA%B0%80+%EB%82%98");
	}

}
