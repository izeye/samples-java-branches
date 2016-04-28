package com.izeye.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by izeye on 16. 4. 28..
 */
public class MessageAuthenticationServiceTests {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	MessageAuthenticationService messageAuthenticationService
			= new DefaultMessageAuthenticationService();
	
	@Test
	public void test() {
		String message = "Hello, world!";
		String mac = this.messageAuthenticationService.createMessageAuthenticationCode(message);
		this.messageAuthenticationService.verifyMessageAuthenticationCode(message, mac);
	}

	@Test
	public void testVerificationFailed() {
		String message = "Hello, world!";
		String mac = this.messageAuthenticationService.createMessageAuthenticationCode(message);

		String fakeMac = "fake mac";
		thrown.expect(MessageAuthenticationFailedException.class);
		thrown.expectMessage("Expected MAC is '" + fakeMac + "' but actual MAC was '" + mac + "'.");
		this.messageAuthenticationService.verifyMessageAuthenticationCode(message, fakeMac);
	}
	
}
