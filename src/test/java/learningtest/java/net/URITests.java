package learningtest.java.net;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link URI}.
 *
 * @author Johnny Lim
 */
public class URITests {

	@Test
	public void testTelLink() throws URISyntaxException {
		URI uri = new URI("tel:1234");
		assertThat(uri.getScheme()).isEqualTo("tel");
		assertThat(uri.getHost()).isNull();
		assertThat(uri.getPort()).isEqualTo(-1);
		assertThat(uri.getPath()).isNull();
		assertThat(uri.getQuery()).isNull();
	}

}
