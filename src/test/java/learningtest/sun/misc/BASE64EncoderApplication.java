package learningtest.sun.misc;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Encoder;

/**
 * Tests for {@link BASE64Encoder}.
 *
 * This test uses JDK internal APIs intentionally for seeing compile-time warnings and
 * testing {@code jdeps} command.
 *
 * @author Johnny Lim
 */
public class BASE64EncoderApplication {

	public static void main(String[] args) throws UnsupportedEncodingException {
		BASE64Encoder encoder = new BASE64Encoder();
		String encoded = encoder.encode("Hello, world!".getBytes("UTF-8"));
		System.out.println(encoded);
	}

}
