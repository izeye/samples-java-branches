package com.izeye.util;

/**
 * Created by izeye on 16. 1. 21..
 */
public abstract class TypeUtils {

	public static Byte[] toWrapped(byte[] bytes) {
		Byte[] wrapped = new Byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			wrapped[i] = bytes[i];
		}
		return wrapped;
	}
	
}
