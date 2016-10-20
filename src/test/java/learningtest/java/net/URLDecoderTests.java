package learningtest.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link URLDecoder}.
 *
 * @author Johnny Lim
 */
public class URLDecoderTests {

	@Test
	public void testDecode() throws UnsupportedEncodingException {
		assertThat(URLDecoder.decode("%EA%B0%80+%EB%82%98", "UTF-8")).isEqualTo("가 나");
		assertThat(URLDecoder.decode("%EA%B0%80%20%EB%82%98", "UTF-8")).isEqualTo("가 나");
	}

}
