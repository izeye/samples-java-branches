package com.izeye.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by izeye on 16. 3. 29..
 */
public abstract class CompressionUtils {

	public static byte[] compress(String text) {
		try {
			byte[] rawBytes = text.getBytes();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
			gzipOut.write(rawBytes);
			gzipOut.close();
			return baos.toByteArray();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String uncompress(byte[] compressedBytes) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(compressedBytes);
			GZIPInputStream gzipIn = new GZIPInputStream(bais);
			
			byte[] buffer = new byte[1024];
			int read;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((read = gzipIn.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
				baos.close();
			}
			return baos.toString();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
