package learningtest.java.net;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link URL}.
 *
 * @author Johnny Lim
 */
public class URLTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testTelLink() throws MalformedURLException {
		this.thrown.expect(MalformedURLException.class);
		this.thrown.expectMessage("unknown protocol: tel");
		new URL("tel:1234");
	}

}
