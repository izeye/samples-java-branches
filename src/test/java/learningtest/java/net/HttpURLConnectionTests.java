package learningtest.java.net;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Tests for {@link HttpURLConnection}.
 *
 * @author Johnny Lim
 */
public class HttpURLConnectionTests {

	@Test
	public void test() throws IOException {
		HttpURLConnection connection = (HttpURLConnection) URI.create("https://www.naver.com").toURL()
				.openConnection();

		// NOTE: This is optional as getInputStream() will invoke anyway.
		connection.connect();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),
				StandardCharsets.UTF_8))) {
			String response = br.lines()
					.collect(Collectors.joining(System.lineSeparator()));
			System.out.println(response);
		}
		connection.disconnect();
	}

}
