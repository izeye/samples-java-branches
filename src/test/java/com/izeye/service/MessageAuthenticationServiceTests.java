package com.izeye.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 4. 28..
 */
public class MessageAuthenticationServiceTests {
	
	private static final String CHARSET_NAME = "UTF-8";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	MessageAuthenticationService messageAuthenticationService
			= new DefaultMessageAuthenticationService();
	
	@Test
	public void test() {
		String message = "Hello, world!";
		try {
			byte[] messageBytes = message.getBytes(CHARSET_NAME);
			byte[] macApplied = this.messageAuthenticationService.applyMessageAuthentication(
					messageBytes);
			byte[] extracted = this.messageAuthenticationService.extractMessage(macApplied);
			assertThat(extracted).isEqualTo(messageBytes);
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void testVerificationFailed() {
		String message = "Hello, world!";
		try {
			byte[] messageBytes = message.getBytes(CHARSET_NAME);
			byte[] macApplied = this.messageAuthenticationService.applyMessageAuthentication(
					messageBytes);
			
			// Manipulate message.
			macApplied[macApplied.length - 1] = 0;

			thrown.expect(MessageAuthenticationFailedException.class);
			thrown.expectMessage("Expected MAC is '");
			this.messageAuthenticationService.extractMessage(macApplied);
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
