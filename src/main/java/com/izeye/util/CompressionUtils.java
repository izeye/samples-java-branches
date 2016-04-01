package com.izeye.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by izeye on 16. 3. 29..
 */
public abstract class CompressionUtils {
	
	private static final int BUFFER_SIZE = 1024;

	public static byte[] compress(byte[] original) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Deflater compresser = new Deflater();
		compresser.setInput(original);
		compresser.finish();
		byte[] buffer = new byte[BUFFER_SIZE];
		int written;
		while ((written = compresser.deflate(buffer)) != 0) {
			baos.write(buffer, 0, written);
		}
		return baos.toByteArray();
	}

	public static byte[] uncompress(byte[] compressed) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Inflater inflater = new Inflater();
		inflater.setInput(compressed);
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		try {
			while ((read = inflater.inflate(buffer)) != 0) {
				baos.write(buffer, 0, read);
			}
			return baos.toByteArray();
		} catch (DataFormatException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
