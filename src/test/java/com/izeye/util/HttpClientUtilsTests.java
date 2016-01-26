package com.izeye.util;

import org.junit.Test;

/**
 * Created by izeye on 16. 1. 26..
 */
public class HttpClientUtilsTests {
	
	@Test
	public void testSendGetRequest() {
//		HttpServerUtils.runEchoServer(18080);
//		String uri = "http://localhost:18080";
		
		String uri = "http://www.google.com/";
		String referrer = "http://www.google.com/";
		int referrerLength = referrer.length();
		for (int i = 0; i < referrerLength; i++) {
			System.out.println(referrer.length());
			try {
				String response = HttpClientUtils.sendGetRequest(uri, referrer + referrer);
				System.out.println(response);
				break;
			} catch (RuntimeException ex) {
				System.out.println(ex);
			}
			referrer = referrer.substring(0, referrer.length() - 1);
		}
	}
	
}
