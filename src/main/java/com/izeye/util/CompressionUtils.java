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
		Deflater compresser = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			compresser = new Deflater();
			compresser.setInput(original);
			compresser.finish();
			byte[] buffer = new byte[BUFFER_SIZE];
			int written;
			while ((written = compresser.deflate(buffer)) != 0) {
				baos.write(buffer, 0, written);
			}
			return baos.toByteArray();
		}
		finally {
			if (compresser != null) {
				// NOTE: This should be called explicitly to avoid possible native memory leak.
				compresser.end();
			}
		}
	}

	public static byte[] uncompress(byte[] compressed) {
		Inflater decompresser = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			decompresser = new Inflater();
			decompresser.setInput(compressed);
			byte[] buffer = new byte[BUFFER_SIZE];
			int read;
			while ((read = decompresser.inflate(buffer)) != 0) {
				baos.write(buffer, 0, read);
			}
			return baos.toByteArray();
		}
		catch (DataFormatException ex) {
			throw new RuntimeException(ex);
		}
		finally {
			if (decompresser != null) {
				// NOTE: This should be called explicitly to avoid possible native memory leak.
				decompresser.end();
			}
		}
	}
	
}
