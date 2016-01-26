package learningtest.org.apache.http.client;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by izeye on 16. 1. 26..
 */
public class HttpClientTests {
	
	@Test
	public void test() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://www.google.com/");
		request.setHeader("Referrer", "http://www.google.com/");
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			InputStream content = response.getEntity().getContent();
			String body = IOUtils.toString(content);
			System.out.println(body);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
