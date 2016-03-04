package com.izeye.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by izeye on 16. 1. 26..
 */
public abstract class HttpClientUtils {
	
	private static final String HEADER_REFERRER = "Referrer";
	private static final String HEADER_REFERER = "Referer";
	
	public static String sendGetRequest(String uri, String referrer) {
		HttpGet request = new HttpGet(uri);
//		request.setHeader(HEADER_REFERRER, referrer);
		request.setHeader(HEADER_REFERER, referrer);
		try {
			CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Unexpected status code: " + statusCode);
			}
			InputStream content = response.getEntity().getContent();
			return IOUtils.toString(content);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
