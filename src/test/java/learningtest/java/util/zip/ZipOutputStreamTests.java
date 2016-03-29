package learningtest.java.util.zip;

import com.izeye.util.CompressionUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 3. 29..
 */
public class ZipOutputStreamTests {
	
	@Test
	public void test() {
		String message = "Hello, world!";
		
		StringBuffer sbText = new StringBuffer();
		for (int i = 0; i < 100; i++) {
			sbText.append(i);
			sbText.append(": ");
			sbText.append(message);
			sbText.append("\n");
		}
		String text = sbText.toString();
		int rawByteLength = text.getBytes().length;
		System.out.println("Raw byte length: " + rawByteLength);

		byte[] compressedBytes = CompressionUtils.compress(text);
		int compressedByteLength = compressedBytes.length;
		System.out.println("Compressed byte length: " + compressedByteLength);

		System.out.println("Compression gain: "
				+ (100 - compressedByteLength * 100 / rawByteLength) + "%");

		String uncompressedText = CompressionUtils.uncompress(compressedBytes);
		System.out.println(uncompressedText);
		assertThat(uncompressedText).isEqualTo(text);
	}

}
