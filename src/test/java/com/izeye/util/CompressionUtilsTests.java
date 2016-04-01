package com.izeye.util;

import org.junit.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 3. 29..
 */
public class CompressionUtilsTests {
	
	@Test
	public void test() {
		String message = "Hello, world!";
		int repeat = 100;
		
		StringBuffer sbText = new StringBuffer();
		for (int i = 0; i < repeat; i++) {
			sbText.append(i);
			sbText.append(": ");
			sbText.append(message);
			sbText.append("\n");
		}
		String text = sbText.toString();

		byte[] original = text.getBytes();
		print("original", original);

		byte[] compressed = CompressionUtils.compress(original);
		print("compressed", compressed);

		System.out.println("Compression gain: "
				+ (100 - compressed.length * 100 / original.length) + "%");

		byte[] uncompressed = CompressionUtils.uncompress(compressed);
		print("uncompressed", uncompressed);
		
		String uncompressedText = new String(uncompressed);
		System.out.println(uncompressedText);
		assertThat(uncompressedText).isEqualTo(text);
	}

	private void print(String header, byte[] bytes) {
		System.out.println(header + ": " + new String(Base64.getEncoder().encode(bytes)));
		System.out.println("Length: " + bytes.length);
	}

}
