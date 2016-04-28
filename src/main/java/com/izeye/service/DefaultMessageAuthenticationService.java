package com.izeye.service;

import com.izeye.util.ByteUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by izeye on 16. 4. 28..
 */
public class DefaultMessageAuthenticationService
		implements MessageAuthenticationService {

	private static final String CHARSET = "UTF-8";

	// See https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#Mac
	private static final String MAC_ALGORITHM = "HmacSHA256";
	
	private static final String KEY = "2a4a2d9215dbb0a0bb8ebc9cce676359db7a322d7cb1bbf5f04f502c19a55e8d9979e372762f96ed94759c1a81666548b6bd1a4e6f924c5d77bed4f2ee1806393ec55339e7ca3ad72b90b742ebc0bf9af1eeae7c54e09bb1fa40c3f13e61a5109a67751897d4507dee7db2fb58e1ab458403ac842091a1871779762a2500ace68058e2f25f3c24fcc402ca13241c3e72f64afe00c4aaedf7caf6c756e9f7c8bb6374be71828803028ef8e5fd4bb4353330528d7619198bb86683c80ec4840fd6b788bd8d8a11a384788379b40a436300927b5f20e904ab70aac9ebf1f482f80bdaf56f0f1a1e9e5557b18e42760318b69be8ac61ab1f95a4438f90c42d598412";
	private static final SecretKeySpec KEY_SPEC;
	static {
		KEY_SPEC = new SecretKeySpec(
				ByteUtils.hexString2Bytes(KEY), MAC_ALGORITHM);
	}

	private static final ThreadLocal<Mac> MAC = new ThreadLocal<Mac>() {
		@Override
		protected Mac initialValue() {
			try {
				Mac mac = Mac.getInstance(MAC_ALGORITHM);
				mac.init(KEY_SPEC);
				return mac;
			} catch (NoSuchAlgorithmException ex) {
				throw new RuntimeException(ex);
			} catch (InvalidKeyException ex) {
				throw new RuntimeException(ex);
			}
		}
	};

	@Override
	public String createMessageAuthenticationCode(String message) {
		try {
			byte[] code = MAC.get().doFinal(message.getBytes(CHARSET));
			return ByteUtils.bytes2HexString(code);
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void verifyMessageAuthenticationCode(String message, String expectedMac) {
		String actualMac = createMessageAuthenticationCode(message);
		if (!actualMac.equals(expectedMac)) {
			throw new MessageAuthenticationFailedException(
					String.format("Expected MAC is '%s' but actual MAC was '%s'.",
							expectedMac, actualMac));
		}
	}
	
}
