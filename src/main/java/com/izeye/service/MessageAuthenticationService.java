package com.izeye.service;

/**
 * Created by izeye on 16. 4. 28..
 */
public interface MessageAuthenticationService {
	
	String createMessageAuthenticationCode(String message);
	
	void verifyMessageAuthenticationCode(String message, String expectedMac);
	
}
