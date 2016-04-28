package com.izeye.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by izeye on 16. 3. 19..
 */
public abstract class ByteUtils {
	
	public static String bytes2HexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			sb.append(byte2HexString(b));
		}
		return sb.toString();
	}
	
	public static String byte2HexString(byte b) {
		return new StringBuilder(2)
				.append(byte2HexChar((byte) ((b >>> 4) & 0xF)))
				.append(byte2HexChar((byte) (b & 0xF))).toString();
	}
	
	public static byte[] hexString2Bytes(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		int byteOffset = 0;
		for (int i = 0; i < hex.length(); i += 2) {
			bytes[byteOffset] = (byte) (hexChar2Byte(hex.charAt(i)) << 4
					| hexChar2Byte(hex.charAt(i + 1)));
			byteOffset++;
		}
		return bytes;
	}
	
	private static final String HEX_CHARS = "0123456789abcdef";
	
	private static final Map<Character, Byte> BYTE_BY_HEX_CHAR;
	static {
		Map<Character, Byte> byteByHexChar = new HashMap<>(16);
		for (int i = 0; i < HEX_CHARS.length(); i++) {
			byteByHexChar.put(HEX_CHARS.charAt(i), (byte) i);
		}
		BYTE_BY_HEX_CHAR = Collections.unmodifiableMap(byteByHexChar);
	}
	
	public static char byte2HexChar(byte b) {
		return HEX_CHARS.charAt(b);
	}
	
	public static byte hexChar2Byte(char hex) {
		return BYTE_BY_HEX_CHAR.get(hex);
	}
	
	public static byte[] randomBytes(int length) {
		byte[] bytes = new byte[length];
		ThreadLocalRandom.current().nextBytes(bytes);
		return bytes;
	}
	
}
