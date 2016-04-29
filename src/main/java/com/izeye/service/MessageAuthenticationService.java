package com.izeye.service;

/**
 * Created by izeye on 16. 4. 28..
 */
public interface MessageAuthenticationService {
	
	byte[] applyMessageAuthentication(byte[] message);
	
	byte[] extractMessage(byte[] macApplied);
	
}
